package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.heb.centralmarket.uicart.MainNavOption
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme

@Composable
fun PagesScreen(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(title = R.string.pages, drawerState = drawerState, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = chevronLeft(),
                        contentDescription = stringResource(id = R.string.back),
                        tint = UICTheme.colorScheme.txt.primary,
                    )
                }
            })
        },
    ) { paddingValues ->
        UICAppTheme {
            Surface {
                val categories =
                    listOf(
                        R.string.sections,
                        R.string.address_book,
                        R.string.fulfilment,
                        R.string.fulfilment_app_bar_toggle,
                    )
                CoreBackground {
                    LazyColumn(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                    ) {
                        itemsIndexed(categories) { index, category ->
                            ListItem(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            when (category) {
                                                R.string.sections ->
                                                    navController.navigate(MainNavOption.SectionsScreen.name)

                                                R.string.address_book ->
                                                    navController.navigate(MainNavOption.AddressBook.name)

                                                R.string.fulfilment ->
                                                    navController.navigate(MainNavOption.Fulfilment.name)

                                                R.string.fulfilment_app_bar_toggle ->
                                                    navController.navigate(MainNavOption.FulfilmentAppBarToggle.name)

                                        }
                                    },
                                headlineContent = {
                                    CoreBodyTextView(
                                        text = stringResource(id = category),
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
                            if (index < categories.size - 1) {
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
}
