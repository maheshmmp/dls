package com.heb.centralmarket.uicart.component.ppg

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.component.CoreCaptionTextView
import com.heb.centralmarket.uicart.component.CoreHeadingTextView
import com.heb.centralmarket.uicart.component.HeadingVariant
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

/**
 * Main countdown timer component with background image and overlay text
 * @param modifier Modifier for styling and layout adjustments
 * @param backgroundImageUrl URL of the background image to display
 * @param textColor Color for countdown text (defaults to white if unspecified)
 * @param backgroundColor Background color for the container (defaults to primary brand color if unspecified)
 * @param targetMillis Timer time left expected in milliseconds
 */
@Composable
fun CountdownTimer(
    modifier: Modifier = Modifier,
    backgroundImageUrl: String,
    textColor: Color,
    backgroundColor: Color,
    targetMillis: Long?,
    heading: String?,
    description: String?,
    onClick: () -> Unit,
) {
    var timeLeftMillis by remember {
        mutableLongStateOf(
            value = targetMillis?.minus(other = System.currentTimeMillis()) ?: 0,
        )
    }
    var isRunning by remember { mutableStateOf(value = true) }

    LaunchedEffect(key1 = isRunning, key2 = timeLeftMillis) {
        if (isRunning && timeLeftMillis > 0) {
            while (timeLeftMillis > 0) {
                delay(timeMillis = 1000L)
                timeLeftMillis -= 1000L
            }
            isRunning = false
        }
    }

    val days = TimeUnit.MILLISECONDS.toDays(timeLeftMillis)
    val hours = TimeUnit.MILLISECONDS.toHours(timeLeftMillis) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeftMillis) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeftMillis) % 60

    // Card container with rounded corners
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .aspectRatio(ratio = CountdownConstants.IMAGE_ASPECT_RATIO),
        shape = UICShape.shapes.mediumRoundCornerShape,
        onClick = { onClick.invoke() },
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize(),
        ) {
            // Background Color
            if (backgroundImageUrl.isBlank()) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(color = backgroundColor),
                )
            }

            // Background Image
            if (backgroundImageUrl.isBlank().not()) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(data = backgroundImageUrl)
                        .crossfade(enable = true)
                        .build(),
                    contentDescription = stringResource(R.string.countdown_timer_content_description),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.uic_cm_banner_placeholder),
                    error = painterResource(id = R.drawable.uic_cm_banner_placeholder),
                    modifier = Modifier.matchParentSize(),
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = UICSpacing.spacing.spacing100),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.weight(weight = 1f)) // Pushes content down

                    CompositionLocalProvider(
                        LocalDensity provides Density(
                            LocalDensity.current.density,
                            fontScale = minOf(a = LocalDensity.current.fontScale, b = 1.4f),
                        )
                    ) {
                        if (!heading.isNullOrEmpty()) {
                            CoreHeadingTextView(
                                text = heading.uppercase(),
                                color = textColor,
                                maxLines = CountdownConstants.MAX_LINES_TITLE,
                                headingLevel = HeadingVariant.HEADING_3,
                                textAlign = TextAlign.Center,
                            )
                        }

                        if (!description.isNullOrEmpty()) {
                            CoreBodyTextView(
                                modifier = Modifier.padding(
                                    bottom = UICSpacing.spacing.spacing050,
                                ),
                                text = description,
                                color = textColor,
                                maxLines = CountdownConstants.MAX_LINES_DESCRIPTION,
                                isBold = true,
                                textAlign = TextAlign.Center,
                                bodyVariant = BodyVariant.BODY_2,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Countdown timer at the bottom
                    CountdownRow(
                        days = days,
                        hours = hours,
                        minutes = minutes,
                        seconds = seconds,
                        textColor = if (textColor.isUnspecified) {
                            UICTheme.colorScheme.txt.primaryInverse
                        } else {
                            textColor
                        },
                    )
                }
            }

        }
    }
}

/**
 * Horizontal row displaying countdown values with colons as separators
 * @param days String value for days remaining
 * @param hours String value for hours remaining
 * @param minutes String value for minutes remaining
 * @param seconds String value for seconds remaining
 * @param textColor Color for all countdown text
 */
@SuppressLint("DefaultLocale")
@Composable
fun CountdownRow(
    days: Long,
    hours: Long,
    minutes: Long,
    seconds: Long,
    textColor: Color,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CountdownItem(
            label = stringResource(R.string.countdown_days),
            value = String.format("%02d", days),
            textColor = textColor,
        )
        Colon(textColor)
        CountdownItem(
            label = stringResource(R.string.countdown_hours),
            value = String.format("%02d", hours),
            textColor = textColor,
        )
        Colon(textColor)
        CountdownItem(
            label = stringResource(R.string.countdown_minutes),
            value = String.format("%02d", minutes),
            textColor = textColor,
        )
        Colon(textColor = textColor)
        CountdownItem(
            label = stringResource(R.string.countdown_seconds),
            value = String.format("%02d", seconds),
            textColor = textColor,
        )
    }
}

/**
 * Individual countdown item with value and label
 * @param label Text label describing the time unit (e.g., "Days", "Hours")
 * @param value Numeric value to display
 * @param textColor Color for both value and label text
 */
@Composable
private fun CountdownItem(
    label: String,
    value: String,
    textColor: Color,
) {
    CompositionLocalProvider(
        value = LocalDensity provides
                Density(
                    density = LocalDensity.current.density,
                    fontScale = minOf(a = LocalDensity.current.fontScale, b = 1.1f),
                ),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CoreHeadingTextView(
                text = value,
                color = textColor,
                headingLevel = HeadingVariant.HEADING_2,
            )
            CoreCaptionTextView(
                text = label,
                color = textColor,
                isBold = true,
            )
        }
    }
}

/**
 * Colon separator between countdown items
 */
@Composable
private fun Colon(textColor: Color) {
    CompositionLocalProvider(
        LocalDensity provides
                Density(
                    LocalDensity.current.density,
                    fontScale = minOf(LocalDensity.current.fontScale, 1.35f),
                ),
    ) {
        CoreBodyTextView(
            text = stringResource(R.string.countdown_colon),
            color = textColor,
            isBold = true,
            modifier = Modifier.padding(horizontal = UICSpacing.spacing.spacing050),
        )
    }
}

// Constants for component configuration
object CountdownConstants {
    const val IMAGE_ASPECT_RATIO = 2f / 1f
    const val MAX_LINES_TITLE = 2
    const val MAX_LINES_DESCRIPTION = 1
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "With Image, Light Mode")
@Composable
fun PreviewCountdownWithImageLight() {
    UICAppTheme {
        CountdownTimer(
            backgroundImageUrl = "https://picsum.photos/600/300",
            modifier = Modifier.fillMaxSize(),
            textColor = Color.White,
            backgroundColor = Color.Black,
            targetMillis = System.currentTimeMillis() + 3600_000, // 1 hour later
            heading = "Event Starts Soon",
            description = "Limited time only",
            onClick = {
            },
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Without Image, Dark Mode")
@Composable
fun PreviewCountdownWithoutImageDark() {
    UICAppTheme {
        CountdownTimer(
            backgroundImageUrl = "",
            modifier = Modifier.fillMaxSize(),
            textColor = Color.White,
            backgroundColor = Color(0xFF121212),
            targetMillis = System.currentTimeMillis() + 86_400_000, // 1 day later
            heading = "Big Launch!",
            description = "Don't miss out!",
            onClick = {
            },
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Minimal Preview")
@Composable
fun PreviewCountdownMinimal() {
    UICAppTheme {
        CountdownTimer(
            backgroundImageUrl = "",
            modifier = Modifier.fillMaxWidth(),
            textColor = Color.Gray,
            backgroundColor = Color.Transparent,
            targetMillis = System.currentTimeMillis() + 5_000,
            heading = "",
            description = "",
            onClick = {
            },
        )
    }
}
