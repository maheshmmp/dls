package com.heb.centralmarket.uicart.component.mapsnapshotview

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.Log
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.createBitmap
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICartThemeSystem
import com.heb.centralmarket.uicart.themesystem.resolve

// Class to hold constant defaults for the map snapshot view
class MapSnapshotView private constructor() {
    companion object {
        const val DEFAULT_ZOOM: Float = 16f
        const val DEFAULT_MAP_TYPE: Int = GoogleMap.MAP_TYPE_NORMAL
        val DEFAULT_PIN_SIZE: Dp = 40.dp
    }
}

/**
 * Displays a static Google Map snapshot with a custom marker at the specified latitude and longitude.
 *
 * This composable is lifecycle-aware and manages the MapView instance efficiently to avoid memory leaks and jitter.
 *
 * @param latitude The latitude for the marker position.
 * @param longitude The longitude for the marker position.
 * @param modifier Modifier to be applied to the map view.
 * @param zoom The zoom level for the map camera.
 * @param mapType The type of map to display (e.g., normal, satellite).
 * @param pinSize The size of the marker pin.
 * @param markerDrawable The drawable resource for the marker icon.
 * @param tint The color tint to apply to the marker icon.
 */
@Composable
fun UICMapSnapshotView(
    latitude: Double,
    longitude: Double,
    modifier: Modifier = Modifier,
    zoom: Float = MapSnapshotView.DEFAULT_ZOOM,
    mapType: Int = MapSnapshotView.DEFAULT_MAP_TYPE,
    pinSize: Dp = MapSnapshotView.DEFAULT_PIN_SIZE,
    @DrawableRes markerDrawable: Int = R.drawable.uic_ic_pin,
    tint: Color = UICTheme.colorScheme.brand.primary.core,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val systemDark = isSystemInDarkTheme()
    val darkModePreference by UICartThemeSystem.darkModeUpdates.collectAsState()
    val isDarkMode = darkModePreference.resolve(systemDark)

    // Keep latest param values accessible inside callbacks without re-triggering effects
    val currentLatitude by rememberUpdatedState(latitude)
    val currentLongitude by rememberUpdatedState(longitude)
    val currentZoom by rememberUpdatedState(zoom)
    val currentMapType by rememberUpdatedState(mapType)
    val currentPinSize by rememberUpdatedState(pinSize)
    val currentMarkerDrawable by rememberUpdatedState(markerDrawable)
    val currentTint by rememberUpdatedState(tint)

    // Retain the MapView across recompositions — never recreate it
    val mapView =
        remember {
            MapView(context).apply {
                id = View.generateViewId()
            }
        }

    // Cache the Google Map instance so update() never calls getMapAsync again
    var googleMapCache by remember { mutableStateOf<GoogleMap?>(null) }

    // Lifecycle wiring — runs once, survives recompositions
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer =
            LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_CREATE -> mapView.onCreate(null)
                    Lifecycle.Event.ON_START -> mapView.onStart()
                    Lifecycle.Event.ON_RESUME -> mapView.onResume()
                    Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                    Lifecycle.Event.ON_STOP -> mapView.onStop()
                    else -> {}
                }
            }
        lifecycle.addObserver(observer)
        // onDispose is always the final teardown point — whether the composable is removed
        // during navigation or because the host lifecycle reaches ON_DESTROY. Calling
        // mapView.onDestroy() here (and nowhere else) guarantees it is called exactly once,
        // avoiding the NullPointerException thrown by the Google Maps native layer when
        // onDestroy() is invoked a second time on an already-torn-down map object.
        onDispose {
            lifecycle.removeObserver(observer)
            googleMapCache = null
            mapView.onDestroy()
        }
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            // factory runs once — initialise the map and cache the GoogleMap instance
            factory = {
                mapView.apply {
                    getMapAsync { googleMap ->
                        googleMapCache = googleMap
                        setupGoogleMap(
                            googleMap = googleMap,
                            latitude = currentLatitude,
                            longitude = currentLongitude,
                            zoom = currentZoom,
                            mapType = currentMapType,
                            context = context,
                            isDarkTheme = isDarkMode,
                            markerDrawable = currentMarkerDrawable,
                            pinSize = currentPinSize,
                            tint = currentTint,
                        )
                    }
                }
            },
            // update runs on recomposition — use the cached map, skip getMapAsync entirely
            update = {
                val cachedMap = googleMapCache
                if (cachedMap != null) {
                    // Map is ready — apply changes immediately, no async hop = no jitter
                    setupGoogleMap(
                        googleMap = cachedMap,
                        latitude = currentLatitude,
                        longitude = currentLongitude,
                        zoom = currentZoom,
                        mapType = currentMapType,
                        context = context,
                        isDarkTheme = isDarkMode,
                        markerDrawable = currentMarkerDrawable,
                        pinSize = currentPinSize,
                        tint = currentTint,
                    )
                }
                // If cachedMap is null the factory's getMapAsync callback will handle setup
            },
        )
    }
}

private fun setupGoogleMap(
    googleMap: GoogleMap,
    latitude: Double,
    longitude: Double,
    zoom: Float,
    mapType: Int,
    context: Context,
    isDarkTheme: Boolean,
    @DrawableRes markerDrawable: Int,
    pinSize: Dp,
    tint: Color,
) {
    googleMap.apply {
        uiSettings.apply {
            isScrollGesturesEnabled = false
            isZoomGesturesEnabled = false
            isRotateGesturesEnabled = false
            isTiltGesturesEnabled = false
            isCompassEnabled = false
            isMyLocationButtonEnabled = false
            isMapToolbarEnabled = false
        }

        if (isDarkTheme) {
            try {
                val success =
                    setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_dark),
                    )
                if (!success) Log.e("Map", "Style parsing failed.")
            } catch (e: Resources.NotFoundException) {
                Log.e("Map", "Can't find style. Error: ", e)
            }
        } else {
            setMapStyle(null)
        }

        isBuildingsEnabled = false
        this.mapType = mapType

        val latLng = LatLng(latitude, longitude)

        // Move camera without animation — animation during a screen transition causes the jitter
        moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))

        clear()
        val bitmapDescriptor = getTintedBitmapDescriptor(context, markerDrawable, pinSize, tint)
        addMarker(
            MarkerOptions()
                .position(latLng)
                .icon(bitmapDescriptor),
        )
    }
}

private fun getTintedBitmapDescriptor(
    context: Context,
    @DrawableRes drawableRes: Int,
    size: Dp,
    tint: Color,
): BitmapDescriptor {
    val drawable =
        AppCompatResources.getDrawable(context, drawableRes)
            ?: throw IllegalArgumentException("Drawable $drawableRes not found!")

    val pxSize = (size.value * context.resources.displayMetrics.density).toInt().coerceAtLeast(1)
    drawable.setBounds(0, 0, pxSize, pxSize)
    drawable.colorFilter = PorterDuffColorFilter(tint.toArgb(), PorterDuff.Mode.SRC_IN)

    val bitmap = createBitmap(pxSize, pxSize)
    val canvas = android.graphics.Canvas(bitmap)
    drawable.draw(canvas)

    return BitmapDescriptorFactory.fromBitmap(bitmap)
}
