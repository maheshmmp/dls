/*
 * Created by Mahesh Mathew Paul on 12/12/24, 12:21 pm
 * mahesh.paul@ust.com
 * Last modified 12/12/24, 12:20 pm
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.UICCircularLoadingIndicatorNoBg
import com.heb.centralmarket.uicart.component.UICWebView
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    navController: NavHostController,
    drawerState: DrawerState
) {
    // URL to be loaded in the WebView. Adds webview=true query param to indicate in-app context.
    val urlString = "https://www.centralmarket.com?webview=true"

    // Track if the page is still loading, to control the visibility of the loading indicator.
    var isLoading by remember { mutableStateOf(true) }

    // Scaffold provides structure with a top app bar and screen body.
    Scaffold(
        topBar = {
            // App bar with title and navigation (back) icon
            AppBar(
                title = R.string.webview_title,
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
    ) { padding ->
        // Core Background wrapper that will include App theme styling
        CoreBackground {
            Box(
                modifier =
                Modifier
                    .fillMaxSize(),
            ) {
                // Configure secure and modern WebView settings
                val applyWebSettings: WebView.() -> Unit = {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.javaScriptCanOpenWindowsAutomatically = true
                    settings.loadsImagesAutomatically = true
                    settings.setSupportMultipleWindows(true)
                    settings.mediaPlaybackRequiresUserGesture = false
                    settings.allowFileAccess = false
                    settings.allowContentAccess = false
                    settings.setGeolocationEnabled(false)
                    settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    scrollBarStyle = android.view.View.SCROLLBARS_INSIDE_OVERLAY
                    // Accept cookies for session persistence if needed
                    android.webkit.CookieManager.getInstance().setAcceptCookie(true)
                }

                // WebViewClient to manage page load lifecycle and errors
                val webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        // Handle internal URL loading inside WebView
                        url?.let { view?.loadUrl(it) }
                        return true // true = don't open in external browser
                    }
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        isLoading = true
                    }
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        isLoading = false
                    }
                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        super.onReceivedError(view, request, error)
                        // Show error message or fallback UI
                    }
                }

                //WebView Component
                UICWebView(
                    url = urlString,
                    modifier = Modifier
                        .padding(paddingValues = padding)
                        .fillMaxSize(),
                    mWebViewClient = webViewClient,
                    webViewSettings = applyWebSettings,
                )

                // Show loading indicator when the page is still loading
                if (isLoading) {
                    UICCircularLoadingIndicatorNoBg(
                        iteration = 0,
                        contentDesc = stringResource(R.string.circular_loading_indicator_description),
                    )
                }
            }
        }
    }
}

