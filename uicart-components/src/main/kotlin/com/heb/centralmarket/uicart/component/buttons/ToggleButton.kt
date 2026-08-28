package com.heb.centralmarket.uicart.component.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.rememberDebouncedCallback

/**
 * Mock Toggle Button.
 *
 * @param toggleCheck for the toggle on/off.
 *  @param toggleDescription for the toggle button state description.
 */
@Composable
fun UICToggleButton(
    toggleCheck: Boolean,
    toggleDescription: String,
    onToggleChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
) {
    // Use the parent-controlled checked value; avoid local visual flip when callback is suppressed.
    val checked = toggleCheck
    val debouncedOnToggleChange = rememberDebouncedCallback(onToggleChange, 500L)
    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
        Switch(
            checked = checked,
            onCheckedChange = {
                debouncedOnToggleChange?.invoke(it)
            },
            modifier =
                modifier
                    .semantics {
                        contentDescription = toggleDescription
                    },
            colors =
                SwitchDefaults.colors(
                    checkedThumbColor = UICTheme.colorScheme.neutral.white,
                    checkedTrackColor = UICTheme.colorScheme.brandSecondary.core,
                    uncheckedThumbColor = UICTheme.colorScheme.neutral.gray1,
                    uncheckedTrackColor = UICTheme.colorScheme.neutral.gray4,
                ),
        )
    }
}

@Preview
@Composable
fun UICToggleButtonCheckedPreview() {
    UICAppTheme {
        _root_ide_package_.com.heb.centralmarket.uicart.component.CoreBackground(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .height(UICHeight.height.large),
        ) {
            UICToggleButton(
                toggleCheck = true,
                toggleDescription = "",
                onToggleChange = null,
                modifier = Modifier.padding(end = UICSpacing.spacing.spacing100),
            )
        }
    }
}

@Preview
@Composable
fun UICToggleButtonUnCheckedPreview() {
    UICAppTheme {
        UICAppTheme {
            _root_ide_package_.com.heb.centralmarket.uicart.component.CoreBackground(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .height(UICHeight.height.large),
            ) {
                UICToggleButton(
                    toggleCheck = false,
                    toggleDescription = "",
                    onToggleChange = null,
                    modifier = Modifier.padding(end = UICSpacing.spacing.spacing100),
                )
            }
        }
    }
}
