package com.heb.centralmarket.uicart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICThemeSystem
import com.heb.centralmarket.uicart.themesystem.UICartThemeSystem

/**
 * Mock Circular loading indicator with lottie animation.
 *
 * @param iteration for the loading, iteration value 1 gives just one iteration and loaded state.Iteration value 1 is for iterate forever.
 * @param contentDesc for the loading content description.
 */
@Composable
fun UICCircularLoadingIndicator(
    iteration: Int,
    contentDesc: String,
    disabled: Boolean = false,
) {
    var iterationCount = iteration
    if (iteration == 0) {
        iterationCount = LottieConstants.IterateForever
    }
    val composition by rememberLottieComposition(
        spec =
            LottieCompositionSpec.RawRes(
                resId =
                    when(UICartThemeSystem.getThemeSystem()){
                        UICThemeSystem.CentralMarket -> R.raw.cm_loading_indicator
                        UICThemeSystem.JoeVs -> R.raw.jvvs_loading_indicator
                        UICThemeSystem.MiTienda -> R.raw.mi_tienda_loading_indicator
                    },
            ),
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
    ) {
        Box(
            modifier =
                Modifier
                    .semantics {
                        contentDescription = contentDesc
                    }
                    .fillMaxSize()
                    .background(
                        color = if (disabled) {
                            UICTheme.colorScheme.structural.bgPrimary
                        } else {
                            Color.Transparent
                        },
                    ),
            contentAlignment = Alignment.Center,
        ) {
            LottieAnimation(
                modifier =
                    Modifier
                        .size(size = UICHeight.height.circularLoading)
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                composition = composition,
                iterations = iterationCount,
            )
        }
    }
}

@Composable
fun UICCircularLoadingIndicatorNoBg(
    iteration: Int,
    contentDesc: String,
) {
    var iterationCount = iteration
    if (iteration == 0) {
        iterationCount = LottieConstants.IterateForever
    }

    // Choose the appropriate Lottie animation based on the theme
    val composition by rememberLottieComposition(
        spec =
            LottieCompositionSpec.RawRes(
                resId =
                    when(UICartThemeSystem.getThemeSystem()){
                        UICThemeSystem.CentralMarket -> R.raw.cm_loading_indicator
                            UICThemeSystem.JoeVs -> R.raw.jvvs_loading_indicator
                        UICThemeSystem.MiTienda -> R.raw.mi_tienda_loading_indicator
                    },
            ),
    )

    Box(
        modifier =
            Modifier
                .fillMaxSize() // Make the box fill the size of its parent
                .semantics { contentDescription = contentDesc }
                .background(color = Color.Transparent),
        // Provide content description
        contentAlignment = Alignment.Center, // Center the Lottie animation
    ) {
        LottieAnimation(
            modifier =
                Modifier
                    .size(UICHeight.height.circularLoading) // Set the size of the animation
                    .align(Alignment.Center) // Align in the center of the box
                    .fillMaxWidth(),
            // Allow the width to be filled if needed
            composition = composition,
            iterations = iterationCount,
        )
    }
}

@Composable
fun UICLoadingLottie(
    iteration: Int,
) {
    var iterationCount = iteration
    if (iteration == 0) {
        iterationCount = LottieConstants.IterateForever
    }

    // Choose the appropriate Lottie animation based on the theme
    val composition by rememberLottieComposition(
        spec =
            LottieCompositionSpec.RawRes(
                resId =
                    when(UICartThemeSystem.getThemeSystem()){
                        UICThemeSystem.CentralMarket -> R.raw.cm_loading_indicator
                        UICThemeSystem.JoeVs -> R.raw.jvvs_loading_indicator
                        UICThemeSystem.MiTienda -> R.raw.mi_tienda_loading_indicator
                    },
            ),
    )

    LottieAnimation(
        modifier =
            Modifier
                .size(size = UICHeight.height.circularLoading) // Set the size of the animation
                .wrapContentSize(),
        // Allow the width to be filled if needed
        composition = composition,
        iterations = iterationCount,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCircularLoadingIndicator() {
    UICCircularLoadingIndicator(1, "")
}

@Preview(showBackground = true)
@Composable
fun PreviewCircularLoadingIndicatorDisable() {
    UICCircularLoadingIndicator(1, "", disabled = true)
}
