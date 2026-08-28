/*
 * Created by Mahesh Mathew Paul on 12/12/24, 12:20 pm
 * mahesh.paul@ust.com
 * Last modified 12/12/24, 12:18 pm
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.heb.centralmarket.uicart.component.appdrawer.AppDrawerContent
import com.heb.centralmarket.uicart.component.appdrawer.AppDrawerItemInfo
import com.heb.centralmarket.uicart.themesystem.UICAppTheme

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {
    UICAppTheme {
        Surface {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    AppDrawerContent(
                        drawerState = drawerState,
                        menuItems = DrawerParams.drawerButtons,
                        defaultPick = MainNavOption.HomeScreen,
                    ) { onUserPickedOption ->
                        when (onUserPickedOption) {
                            MainNavOption.HomeScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.SettingsScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.ColorScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.FontScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.StyleModifiers -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.PrimaryButtonScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.SecondaryButtonScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.HeadersScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.SectionsScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.ToggleButton -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.RadioButton -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.LoadingIndicator -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.IconButtons -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.WebView -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.CountdownTimer -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.EmptyStates -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.AddressBook -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.Fulfilment -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.FulfilmentAppBarToggle -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.RadioButtonScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.InputTextFieldScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.ButtonsChipsTabsScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.ActionsScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.ContentfulBannersScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.InputCategoryScreen, MainNavOption.SearchBarScreen,
                            MainNavOption.PagesCategoryScreen, MainNavOption.TertiaryButtonScreen,
                            MainNavOption.LinkButtonScreen, MainNavOption.SnapShotMap,
                            -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.BottomComponents -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.BottomModelScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.BottomButtonContainerScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.FeedbackScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }
                            MainNavOption.DateSelectorHorizontalScreen -> {
                                navController.navigate(onUserPickedOption.name){
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }
                            MainNavOption.TimeSlotSelectorScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }
                            MainNavOption.LocationScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }
                            MainNavOption.DateSelectorHorizontalViewScreen -> {
                                navController.navigate(onUserPickedOption.name){
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }

                            MainNavOption.ActionCardScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }
                        }
                    }
                },
            ) {
                NavHost(
                    navController = navController,
                    startDestination = NavRoutes.MainRoute.name,
                ) {
                    mainGraph(navController = navController, drawerState)
                }
            }
        }
    }
}

object DrawerParams {
    val drawerButtons =
        arrayListOf(
            AppDrawerItemInfo(
                MainNavOption.HomeScreen,
                R.string.home_title,
                R.drawable.shopping_cart_24dp,
                R.string.drawer_home_description,
            ),
            AppDrawerItemInfo(
                MainNavOption.ColorScreen,
                R.string.drawer_about,
                R.drawable.baseline_color_lens_24,
                R.string.drawer_info_description,
            ),
            AppDrawerItemInfo(
                MainNavOption.FontScreen,
                R.string.font_title,
                R.drawable.frame_1208,
                R.string.drawer_info_description,
            ),
            AppDrawerItemInfo(
                MainNavOption.StyleModifiers,
                R.string.style_modifiers,
                R.drawable.baseline_style_24,
                R.string.drawer_info_description,
            ),
            AppDrawerItemInfo(
                MainNavOption.SettingsScreen,
                R.string.drawer_settings,
                R.drawable.ic_settings,
                R.string.drawer_settings_description,
            ),
        )
}

enum class NavRoutes {
    MainRoute,
}
