/*
 *
 *  Created by Mahesh Paul on 1/15/26, 11:30 AM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/15/26, 9:44 AM
 *
 */

package com.heb.centralmarket.uicart

import androidx.compose.material3.DrawerState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.heb.centralmarket.uicart.screens.ActionCardScreen
import com.heb.centralmarket.uicart.screens.ActionsScreen
import com.heb.centralmarket.uicart.screens.BottomButtonContainerScreen
import com.heb.centralmarket.uicart.screens.BottomComponentsScreen
import com.heb.centralmarket.uicart.screens.BottomModelSheetScreen
import com.heb.centralmarket.uicart.screens.ButtonChipsTabsScreen
import com.heb.centralmarket.uicart.screens.CircularLoadingIndicatorScreen
import com.heb.centralmarket.uicart.screens.ColorScreen
import com.heb.centralmarket.uicart.screens.ContentfulBannersScreen
import com.heb.centralmarket.uicart.screens.DateHorizontalViewScreen
import com.heb.centralmarket.uicart.screens.EmptyStateScreen
import com.heb.centralmarket.uicart.screens.FeedbackScreen
import com.heb.centralmarket.uicart.screens.FontScreen
import com.heb.centralmarket.uicart.screens.FulfilmentScreen
import com.heb.centralmarket.uicart.screens.HeaderFulfilmentToggleScreen
import com.heb.centralmarket.uicart.screens.HomeScreen
import com.heb.centralmarket.uicart.screens.InputScreen
import com.heb.centralmarket.uicart.screens.InputTextFieldShowcaseScreen
import com.heb.centralmarket.uicart.screens.LinkButtonScreen
import com.heb.centralmarket.uicart.screens.LocationCardScreen
import com.heb.centralmarket.uicart.screens.LocationDetailsScreen
import com.heb.centralmarket.uicart.screens.MapSnapshotScreen
import com.heb.centralmarket.uicart.screens.PagesScreen
import com.heb.centralmarket.uicart.screens.PassportGermany
import com.heb.centralmarket.uicart.screens.PrimaryButtonScreen
import com.heb.centralmarket.uicart.screens.RadioButtonScreen
import com.heb.centralmarket.uicart.screens.SearchBarScreen
import com.heb.centralmarket.uicart.screens.SecondaryButtonScreen
import com.heb.centralmarket.uicart.screens.SectionsScreen
import com.heb.centralmarket.uicart.screens.SettingsScreen
import com.heb.centralmarket.uicart.screens.StyleModifierScreen
import com.heb.centralmarket.uicart.screens.TertiaryButtonScreen
import com.heb.centralmarket.uicart.screens.TimeSlotSelectorScreen
import com.heb.centralmarket.uicart.screens.ToggleButtons
import com.heb.centralmarket.uicart.screens.TopAppBarSection
import com.heb.centralmarket.uicart.screens.UICIconButtonScreen
import com.heb.centralmarket.uicart.screens.WebViewScreen

fun NavGraphBuilder.mainGraph(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    navigation(startDestination = MainNavOption.HomeScreen.name, route = NavRoutes.MainRoute.name) {
        composable(MainNavOption.HomeScreen.name) {
            HomeScreen(navController, drawerState)
        }
        composable(MainNavOption.SettingsScreen.name) {
            SettingsScreen(drawerState)
        }
        composable(MainNavOption.ColorScreen.name) {
            ColorScreen(drawerState)
        }
        composable(MainNavOption.FontScreen.name) {
            FontScreen(drawerState)
        }
        composable(MainNavOption.StyleModifiers.name) {
            StyleModifierScreen(drawerState)
        }
        composable(MainNavOption.PrimaryButtonScreen.name) {
            PrimaryButtonScreen(navController, drawerState)
        }
        composable(MainNavOption.SecondaryButtonScreen.name) {
            SecondaryButtonScreen(navController, drawerState)
        }
        composable(MainNavOption.HeadersScreen.name) {
            TopAppBarSection(navController, drawerState)
        }
        composable(MainNavOption.ToggleButton.name) {
            ToggleButtons(navController, drawerState)
        }
        composable(MainNavOption.RadioButton.name) {
            RadioButtonScreen(navController, drawerState)
        }
        composable(MainNavOption.SectionsScreen.name) {
            SectionsScreen(navController, drawerState)
        }
        composable(MainNavOption.LoadingIndicator.name) {
            CircularLoadingIndicatorScreen(navController, drawerState)
        }
        composable(MainNavOption.IconButtons.name) {
            UICIconButtonScreen(navController, drawerState)
        }
        composable(MainNavOption.WebView.name) {
            WebViewScreen(navController, drawerState)
        }
        composable(MainNavOption.CountdownTimer.name) {
            PassportGermany(navController, drawerState)
        }
        composable(MainNavOption.EmptyStates.name) {
            EmptyStateScreen(navController, drawerState)
        }
        composable(MainNavOption.SnapShotMap.name) {
            MapSnapshotScreen(navController, drawerState)
        }
        composable(MainNavOption.AddressBook.name) {
            LocationDetailsScreen(navController, drawerState)
        }
        composable(MainNavOption.Fulfilment.name) {
            FulfilmentScreen(navController, drawerState)
        }
        composable(MainNavOption.FulfilmentAppBarToggle.name) {
            HeaderFulfilmentToggleScreen(navController)
        }
        composable(MainNavOption.RadioButtonScreen.name) {
            RadioButtonScreen(navController, drawerState)
        }
        composable(MainNavOption.InputTextFieldScreen.name) {
            InputTextFieldShowcaseScreen(navController, drawerState)
        }
        composable(MainNavOption.ButtonsChipsTabsScreen.name) {
            ButtonChipsTabsScreen(navController, drawerState)
        }
        composable(MainNavOption.ActionsScreen.name) {
            ActionsScreen(navController, drawerState)
        }
        composable(MainNavOption.ContentfulBannersScreen.name) {
            ContentfulBannersScreen(navController, drawerState)
        }
        composable(MainNavOption.InputCategoryScreen.name) {
            InputScreen(navController, drawerState)
        }
        composable(MainNavOption.SearchBarScreen.name) {
            SearchBarScreen(navController, drawerState)
        }
        composable(MainNavOption.PagesCategoryScreen.name) {
            PagesScreen(navController, drawerState)
        }
        composable(MainNavOption.LinkButtonScreen.name) {
            LinkButtonScreen(navController, drawerState)
        }
        composable(MainNavOption.BottomModelScreen.name) {
            BottomModelSheetScreen(navController, drawerState)
        }
        composable(MainNavOption.TertiaryButtonScreen.name) {
            TertiaryButtonScreen(navController, drawerState)
        }
        composable(MainNavOption.BottomButtonContainerScreen.name) {
            BottomButtonContainerScreen(navController, drawerState)
        }
        composable(MainNavOption.BottomComponents.name) {
            BottomComponentsScreen(navController, drawerState)
        }
        composable(MainNavOption.FeedbackScreen.name) {
            FeedbackScreen(navController, drawerState)
        }
        composable(MainNavOption.DateSelectorHorizontalScreen.name) {
            DateHorizontalViewScreen(navController, drawerState)
        }
        composable(MainNavOption.TimeSlotSelectorScreen.name) {
            TimeSlotSelectorScreen(navController, drawerState)
        }
        composable(MainNavOption.LocationScreen.name) {
            LocationCardScreen(navController, drawerState)
        }
        composable(MainNavOption.ActionCardScreen.name) {
            ActionCardScreen(navController, drawerState)
        }
    }
}

enum class MainNavOption {
    HomeScreen,
    ColorScreen,
    SettingsScreen,
    FontScreen,
    StyleModifiers,
    PrimaryButtonScreen,
    SecondaryButtonScreen,
    TertiaryButtonScreen,
    HeadersScreen,
    SectionsScreen,
    ToggleButton,
    RadioButton,
    LoadingIndicator,
    IconButtons,
    WebView,
    CountdownTimer,
    EmptyStates,
    AddressBook,
    Fulfilment,
    FulfilmentAppBarToggle,
    RadioButtonScreen,
    InputTextFieldScreen,
    ButtonsChipsTabsScreen,
    ActionsScreen,
    ContentfulBannersScreen,
    InputCategoryScreen,
    PagesCategoryScreen,
    SearchBarScreen,
    SnapShotMap,
    LinkButtonScreen,
    BottomComponents,
    BottomModelScreen,
    BottomButtonContainerScreen,
    FeedbackScreen,
    DateSelectorHorizontalScreen,
    TimeSlotSelectorScreen,
    LocationScreen,
    DateSelectorHorizontalViewScreen,
    ActionCardScreen,
}
