package com.heb.centralmarket.uicart.component.bottomSheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.heb.centralmarket.uicart.themesystem.UICCornerRadius
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import kotlinx.coroutines.launch

/**
 * ## UICBottomModalSheet
 *
 * A reusable **Modal Bottom Sheet** implementation built using **Material 3** and **Jetpack Compose**.
 *
 * ### Functional Overview
 * - Displays a draggable modal sheet that slides up from the bottom.
 * - Includes a **custom animated header** (drag handle, title, close button).
 * - Supports scrollable dynamic content via the [content] lambda.
 * - Supports a fixed footer (e.g., button bar) via the optional [floatingContent] lambda.
 * - Provides **controlled height behavior** using [heightType], with a default optimized for most use cases.
 *
 * ### Height Behavior
 * The bottom sheet height is controlled via [heightType]:
 *
 * - [BottomSheetHeightType.EXPANDED] *(default)* → Occupies ~90% of screen height.
 * - [BottomSheetHeightType.WRAP_CONTENT] → Adjusts height based on content size.
 * - [BottomSheetHeightType.FULL_SCREEN] → Occupies full screen height.
 * - [BottomSheetHeightType.CUSTOM_DEFINED] → Allows explicit height control using:
 *    - [customHeight] for fixed `Dp` height
 *    - [customHeightFraction] for screen-based fractional height (0f–1f)
 *
 * > ⚠️ When using [BottomSheetHeightType.CUSTOM_DEFINED], either [customHeight] or
 * > [customHeightFraction] must be provided (but not both).
 *
 * ---
 *
 * ### Parameters
 * @param modifier Modifier to be applied to the bottom sheet container.
 * @param showModal Controls visibility of the modal. If `false`, the composable is not rendered.
 * @param title Optional title displayed in the header. If null or blank, header adjusts accordingly.
 * @param onDismiss Callback invoked when the sheet is dismissed (drag, outside tap, or close action).
 * @param disableInteraction If `true`, blocks all user interactions within the sheet using an overlay.
 * @param heightType Defines how the height of the bottom sheet should be calculated.
 * Defaults to [BottomSheetHeightType.EXPANDED] (~90% of screen height).
 * @param customHeight Fixed height in `Dp`, used only when [heightType] is [BottomSheetHeightType.CUSTOM].
 * @param customHeightFraction Fraction of screen height (0f–1f), used only when [heightType] is [BottomSheetHeightType.CUSTOM].
 * @param content Main body content of the bottom sheet.
 * - Scrollable when height is constrained (EXPANDED / FULL_SCREEN / CUSTOM).
 * - Non-scrollable in WRAP_CONTENT mode.
 * @param floatingContent Optional composable pinned to the bottom of the sheet.
 * Typically used for persistent actions (e.g., buttons).
 *
 * ### Usage Guidelines
 * - Prefer the default [BottomSheetHeightType.WRAP_CONTENT] for least screens (~80-90% use cases).
 * - Use [BottomSheetHeightType.WRAP_CONTENT] for lightweight or small content sheets.
 * - Use [BottomSheetHeightType.FULL_SCREEN] for immersive flows (e.g., forms).
 * - Use [BottomSheetHeightType.CUSTOM_DEFINED] only for special layout requirements.
 *
 * ---
 *
 * ### Notes
 * - Avoid nesting scrollable components (e.g., LazyColumn) inside [content] when
 *   verticalScroll is enabled, unless properly handled.
 * - This component is optimized for reuse and consistency across multiple features.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UICBottomModalSheet(
    modifier: Modifier = Modifier,
    showModal: Boolean,
    title: String? = null,
    onDismiss: () -> Unit,
    disableInteraction: Boolean = false,
    heightType: BottomSheetHeightType = BottomSheetHeightType.WRAP_CONTENT,
    customHeight: Dp? = null,
    customHeightFraction: Float? = null,
    content: @Composable ColumnScope.() -> Unit,
    floatingContent: @Composable (() -> Unit)? = null,
) {
    if (!showModal) return
    require(!(customHeight != null && customHeightFraction != null)) {
        "Provide either customHeight OR customHeightFraction, not both"
    }
    require(
        heightType != BottomSheetHeightType.CUSTOM_DEFINED ||
                customHeight != null ||
                customHeightFraction != null
    ) {
        "CUSTOM_DEFINED heightType requires customHeight or customHeightFraction"
    }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current
    val containerHeightDp = with(density) { windowInfo.containerSize.height.toDp() }
    val calculatedHeight: Dp? = when (heightType) {
        BottomSheetHeightType.WRAP_CONTENT -> null

        BottomSheetHeightType.EXPANDED -> containerHeightDp * 0.9f

        BottomSheetHeightType.FULL_SCREEN -> containerHeightDp

        BottomSheetHeightType.CUSTOM_DEFINED -> {
            when {
                customHeight != null -> customHeight
                customHeightFraction != null -> {
                    val safeFraction = customHeightFraction.coerceIn(0.1f, 1f)
                    containerHeightDp * safeFraction
                }
                else -> null
            }
        }
    }
    var headerHeightPx by remember { mutableIntStateOf(0) }

    ModalBottomSheet(
        onDismissRequest = {
            coroutineScope.launch {
                sheetState.hide()
                onDismiss()
            }
        },
        dragHandle = { },
        sheetState = sheetState,
        containerColor = UICTheme.colorScheme.txt.primaryInverse,
        tonalElevation = UICSpacing.spacing.spacing075,
        shape =
            RoundedCornerShape(
                topStart = UICCornerRadius.borderRadius.large,
                topEnd = UICCornerRadius.borderRadius.large,
            ),
        modifier = modifier.imePadding(),
        contentWindowInsets = { WindowInsets.ime.union(WindowInsets.navigationBars) },
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .then(
                        other = if (calculatedHeight != null) {
                            Modifier.height(calculatedHeight)
                        } else {
                            Modifier.wrapContentHeight()
                        }
                    )
                    .imePadding()
                    .navigationBarsPadding(),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .imePadding()
                        .navigationBarsPadding()
                        .verticalScroll(scrollState)
                        .padding(top = UICSpacing.spacing.spacing300)
                        .zIndex(0f)
                        .let {
                            if (!title.isNullOrBlank()) {
                                it.padding(
                                    top =
                                        with(LocalDensity.current) {
                                            if (headerHeightPx > 12) {
                                                (headerHeightPx).toDp() - UICSpacing.spacing.spacing075
                                            } else {
                                                (headerHeightPx).dp
                                            }
                                        },
                                )
                            } else {
                                it
                            }
                        },
                content = content,
            )

            floatingContent?.let {
                Box(
                    modifier = Modifier.align(Alignment.BottomCenter),
                ) { it() }
            }

            Box(
                modifier =
                    Modifier
                        .align(Alignment.TopCenter)
                        .onGloballyPositioned { coordinates ->
                            headerHeightPx = coordinates.size.height
                        },
            ) {
                AnimatedBottomSheetHeader(
                    title = title,
                    scrollOffset = scrollState.value.toFloat(),
                    onClose = {
                        coroutineScope.launch {
                            sheetState.hide()
                            onDismiss()
                        }
                    },
                )
            }

            // ✅ Touch-blocking overlay
            if (disableInteraction) {
                Box(
                    Modifier
                        .matchParentSize()
                        .zIndex(10f)
                        .pointerInput(Unit) {
                            awaitPointerEventScope {
                                while (true) {
                                    val event = awaitPointerEvent()
                                    event.changes.forEach { it.consume() }
                                }
                            }
                        },
                )
            }
        }
    }
}
enum class BottomSheetHeightType {
    WRAP_CONTENT,
    EXPANDED,
    FULL_SCREEN,
    CUSTOM_DEFINED
}
