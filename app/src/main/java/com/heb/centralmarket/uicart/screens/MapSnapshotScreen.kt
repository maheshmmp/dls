package com.heb.centralmarket.uicart.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.mapsnapshotview.UICMapSnapshotView
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapSnapshotScreen(
    navController: NavHostController,
    drawerState: DrawerState
) {
    // Scaffold provides structure with a top app bar and screen body.
    Scaffold(
        topBar = {
            // App bar with title and navigation (back) icon
            AppBar(
                title = R.string.mapSnapshot,
                drawerState = drawerState,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = chevronLeft(),
                            contentDescription = stringResource(id = R.string.back),
                            tint = UICTheme.colorScheme.txt.primary,
                        )
                    }
                },
                actionIcon = {
                    ThemeSwitcherAction()
                    DarkModeSwitcherAction()
                }
            )
        },
    ) { paddingValues ->
        // Core Background wrapper that will include App theme styling
        CoreBackground {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(paddingValues)
            ) {
                UICMapSnapshotView(
                    latitude = 37.3349,
                    longitude = -122.0090,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f)
                )
            }
        }
    }
}

