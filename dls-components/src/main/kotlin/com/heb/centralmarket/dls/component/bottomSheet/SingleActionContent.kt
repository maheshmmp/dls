package com.heb.centralmarket.uicart.component.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.heb.centralmarket.uicart.component.buttons.UICPrimaryButton
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

/**
 * Displays a dual-action content layout with a title, message, and two buttons.
 *
 * @param title The title text displayed at the top.
 * @param message The message text displayed below the title.
 * @param primaryText The text for the primary action button.
 * @param onPrimaryClick Callback invoked when the primary button is clicked.
 */
@Composable
fun SingleActionContent(
    title: String,
    message: String,
    primaryText: String,
    onPrimaryClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = UICSpacing.spacing.spacing100),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = UICTypography.typography.heading.h2,
            color = UICTheme.colorScheme.txt.primary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(UICSpacing.spacing.spacing050))

        Text(
            text = message,
            style = UICTypography.typography.body.body1.regular,
            color = UICTheme.colorScheme.txt.primary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(UICSpacing.spacing.spacing100))

        UICPrimaryButton(
            onClick = onPrimaryClick,
            buttonText = primaryText,
            isButtonFullWidth = true,
            enabled = true,
            isLoading = false,
        )
        Spacer(Modifier.height(UICSpacing.spacing.spacing100))
    }
}
