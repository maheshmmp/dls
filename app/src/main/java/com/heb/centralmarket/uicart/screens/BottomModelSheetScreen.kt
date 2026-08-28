package com.heb.centralmarket.uicart.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.component.UICInputTextField
import com.heb.centralmarket.uicart.component.bottomSheet.UICBottomModalSheet
import com.heb.centralmarket.uicart.component.buttons.CoreButtonSize
import com.heb.centralmarket.uicart.component.buttons.UICPrimaryButton
import com.heb.centralmarket.uicart.component.buttons.UICTertiaryButton
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICExtendedTheme
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomModelSheetScreen(
    navController: NavController,
    drawerState: DrawerState,
) {
    var showModal by remember { mutableStateOf(false) }
    var selectedView by remember { mutableStateOf(ComposeViewType.NONE) }
    val items =
        listOf(
            ComposeViewType.BASE_VIEW,
            ComposeViewType.TWO_BUTTON_VIEW,
            ComposeViewType.HORIZONTAL_SCROLL,
            ComposeViewType.HELP,
            ComposeViewType.VERTICAL_SCROLL,
            ComposeViewType.VERTICAL_SCROLL_IMAGE_NO_TITLE,
        )

    Scaffold(
        topBar = {
            AppBar(title = R.string.input, drawerState = drawerState, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = chevronLeft(),
                        contentDescription = stringResource(id = R.string.back),
                        tint = UICTheme.colorScheme.txt.primary,
                    )
                }
            }, actionIcon = {
                ThemeSwitcherAction()
                DarkModeSwitcherAction()
            })
        },
    ) { innerPadding ->
        UICAppTheme {
            Surface {
                CoreBackground {
                    LazyColumn(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                    ) {
                        itemsIndexed(items) { index, viewType ->
                            ListItem(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedView = viewType
                                            showModal = true
                                        },
                                headlineContent = {
                                    CoreBodyTextView(
                                        text = viewType.desc,
                                        isBold = false,
                                        textAlign = TextAlign.Start,
                                    )
                                },
                                overlineContent = {
                                    CoreBodyTextView(
                                        text = viewType.title,
                                        isBold = true,
                                        textAlign = TextAlign.Start,
                                    )
                                },
                                shadowElevation = UICSpacing.spacing.spacing050,
                                colors =
                                    ListItemColors(
                                        containerColor = UICTheme.colorScheme.structural.bgPrimary,
                                        headlineColor = UICTheme.colorScheme.structural.bgPrimary,
                                        leadingIconColor = UICTheme.colorScheme.structural.bgPrimary,
                                        overlineColor = UICTheme.colorScheme.structural.bgPrimary,
                                        supportingTextColor = UICTheme.colorScheme.structural.bgPrimary,
                                        trailingIconColor = UICTheme.colorScheme.structural.bgPrimary,
                                        disabledHeadlineColor = UICTheme.colorScheme.structural.bgPrimary,
                                        disabledLeadingIconColor = UICTheme.colorScheme.structural.bgPrimary,
                                        disabledTrailingIconColor = UICTheme.colorScheme.structural.bgPrimary,
                                    ),
                            )
                            if (index < items.size - 1) {
                                HorizontalDivider(
                                    color = UICTheme.colorScheme.structural.outlineLight,
                                    thickness = 1.dp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    val floatingContent: @Composable (() -> Unit)? =
        when (selectedView) {
            ComposeViewType.VERTICAL_SCROLL -> {
                {
                    Box(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        UICPrimaryButton(
                            onClick = { /* handle click */ },
                            buttonText = "Continue",
                            isButtonFullWidth = true,
                            modifier = Modifier.padding(16.dp),
                        )
                    }
                }
            }

            else -> null
        }
    UICBottomModalSheet(
        showModal = showModal,
        title = selectedView.title,
        onDismiss = {
            showModal = false
            selectedView = ComposeViewType.NONE
        },
        content = {
            when (selectedView) {
                ComposeViewType.BASE_VIEW -> BaseViewContent()
                ComposeViewType.TWO_BUTTON_VIEW -> TwoButtonViewContent()
                ComposeViewType.HORIZONTAL_SCROLL -> HorizontalScrollContent()
                ComposeViewType.HELP -> HelpContent()
                ComposeViewType.VERTICAL_SCROLL -> VerticalScrollContent()
                ComposeViewType.VERTICAL_SCROLL_IMAGE_NO_TITLE -> VerticalScrollWithImageAndNoTitleContent()
                else -> Text("Select an item from the list.")
            }
        },
        floatingContent = floatingContent,
    )
}

enum class ComposeViewType(
    val title: String,
    val desc: String,
) {
    BASE_VIEW(title = "Basic view", desc = "Basic view with , title , text content and button"),
    TWO_BUTTON_VIEW(
        title = "Two Button View",
        desc = "Content have , title , text content , Primary and secondary button",
    ),
    HORIZONTAL_SCROLL(title = "Horizontal scroll", desc = "Carousel implementation "),
    HELP(title = "Input", desc = "Screens like support pin with Keyboard"),
    VERTICAL_SCROLL(
        title = "Vertical scroll",
        desc = "Vertical Scroll with maximum height and title",
    ),
    VERTICAL_SCROLL_IMAGE_NO_TITLE(
        title = "",
        desc = "Image and no title",
    ),
    NONE("", ""),
}

@Composable
fun BaseViewContent() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
    ) {
        Text(
            text =
                "Title",
            style = UICTypography.typography.heading.h2,
            color = UICTheme.colorScheme.txt.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text =
                "Lorem ipsum dolor sit amet, consectetur adipiscing " +
                    "elit.Ut tincidunt enim at mauris consectetur, a" +
                    " condimentum ipsum ultricies. ",
            style = UICTypography.typography.body.body1.regular,
            color = UICTheme.colorScheme.txt.primary,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))

        UICPrimaryButton(onClick = {}, buttonText = "primary", isButtonFullWidth = true)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun TwoButtonViewContent() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
    ) {
        Text(
            text =
                "Title",
            style = UICTypography.typography.heading.h2,
            color = UICTheme.colorScheme.txt.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text =
                "Lorem ipsum dolor sit amet, consectetur adipiscing " +
                    "elit.Ut tincidunt enim at mauris consectetur, a" +
                    " condimentum ipsum ultricies. ",
            style = UICTypography.typography.body.body1.regular,
            color = UICTheme.colorScheme.txt.primary,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))

        UICPrimaryButton(onClick = {}, buttonText = "primary", isButtonFullWidth = true)
        Spacer(modifier = Modifier.height(2.dp))

        UICTertiaryButton(
            onClick = {},
            buttonText = "secondary cta",
            isButtonFullWidth = true,
            enabled = true,
            buttonSize = CoreButtonSize.MEDIUM,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun HorizontalScrollContent() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
    ) {
        val scrollState = rememberScrollState()

        Text(
            text =
                "Title",
            style = UICTypography.typography.subtitle.subTitle.bold,
            color = UICTheme.colorScheme.txt.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        )
        Row(verticalAlignment = Alignment.Top) {
            Text(
                text =
                    "Collection Header ",
                style = UICTypography.typography.heading.h3,
                color = UICTheme.colorScheme.txt.primary,
                textAlign = TextAlign.Start,
                modifier =
                    Modifier
                        .weight(1f),
            )
            Text(
                "View All",
                style = UICTypography.typography.heading.h4,
                color = UICTheme.colorScheme.brand.interactive.core,
                modifier = Modifier.padding(end = 16.dp),
            )
        }
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
                    .padding(vertical = 8.dp),
        ) {
            repeat(10) { index ->
                Card(
                    modifier =
                        Modifier
                            .padding(end = 12.dp)
                            .width(120.dp)
                            .height(180.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("Item $index", style = UICTypography.typography.body.body1.regular)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        UICPrimaryButton(
            onClick = {},
            buttonText = "primary",
            isButtonFullWidth = true,
            modifier = Modifier.padding(end = 16.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun VerticalScrollContent() {
    Box(
        modifier =
            Modifier
                .fillMaxSize(),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp), // Space for button
        ) {
            Text(
                text =
                    "Title",
                style = UICTypography.typography.subtitle.subTitle.bold,
                color = UICTheme.colorScheme.txt.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            )
            Text(
                text = "Choose a replacement",
                style = UICTypography.typography.body.body1.regular,
                color = UICTheme.colorScheme.txt.primary,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(vertical = 12.dp),
            )

            // ✅ List of cards
            repeat(20) { index ->
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .height(80.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Item $index",
                            style = UICTypography.typography.body.body1.regular,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun VerticalScrollWithImageAndNoTitleContent() {
    Column(
        modifier =
            Modifier
                .fillMaxSize(),
    ) {
        Box(
            modifier =
                Modifier
                    .background(
                        color = UICExtendedTheme.colorScheme.systemBlack,
                        shape = RoundedCornerShape(UICSpacing.spacing.spacing075),
                    ),
        ) {
            AsyncImage(
                model = "https://picsum.photos/400/200",
                contentDescription = "contentDescription",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

        // ✅ List of cards
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
        ) {
            repeat(20) { index ->
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .height(80.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Item $index",
                            style = UICTypography.typography.body.body1.regular,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun HelpContent() {
    var text by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    start = UICSpacing.spacing.spacing100,
                    end = UICSpacing.spacing.spacing100,
                ),
    ) {
        Text(
            text =
                "Apply a gift card",
            style = UICTypography.typography.subtitle.subTitle.bold,
            color = UICTheme.colorScheme.txt.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        )
        UICInputTextField(
            value = text,
            onValueChange = { text = it },
            label = stringResource(R.string.label),
            enabled = true,
            singleLine = false,
            maxLines = 4,
            isMultilineTextAreaNeeded = false,
            maxChar = 400,
            isError = false,
            errorText = null,
            helperText = stringResource(R.string.helper),
            scaleFactor = 1f,
            keyboardOptions =
                KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
        )
        UICInputTextField(
            value = pin,
            onValueChange = { pin = it },
            label = "Pin",
            enabled = true,
            singleLine = true,
            maxLines = 1,
            isMultilineTextAreaNeeded = false,
            isError = false,
            errorText = null,
            scaleFactor = 1f,
            keyboardOptions =
                KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        UICPrimaryButton(onClick = {}, buttonText = "primary", isButtonFullWidth = true)
        Spacer(modifier = Modifier.height(16.dp))
    }
}
