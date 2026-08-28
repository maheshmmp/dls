package com.heb.centralmarket.uicart.component

/**
 * Author: Ritu Varma G
 * Date Created: 10-07-2025
 * Last Modified: 10-07-2025
 */
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

/**
 * A reusable composable that wraps Android's native [WebView] inside Compose using [AndroidView].
 *
 * ### Purpose:
 * `UICWebView` allows developers to embed a WebView in a Jetpack Compose screen, with full support
 * for injecting a custom [WebViewClient] and applying browser-like settings.
 *
 * It’s designed to be flexible and lightweight, and handles:
 * - Dynamic URL loading
 * - Preventing unnecessary reloads
 * - External WebView configuration (e.g., enabling JavaScript, DOM storage)
 *
 * ### Usage:
 * Use this component whenever a WebView needs to be rendered within a Compose-based screen.
 * Typically used for rendering help pages, terms & conditions, profile settings, etc.
 *
 * ---
 *
 * @param url The URL to be loaded into the WebView.
 *            - If the same URL is passed again, it won't be reloaded.
 * @param modifier Modifier for styling/layout purposes (e.g., size, padding).
 * @param mWebViewClient A required instance of [WebViewClient] to handle events such as page loading, errors, etc.
 *                       Use the `provideCMWebViewClient()` extension to quickly generate a configurable client.
 * @param webViewSettings An optional lambda to apply custom settings to the WebView.
 *                        Recommended to use `applyCMWebSettings()` for consistent configuration across modules.
 *
 */
@Composable
fun UICWebView(
    url: String,
    modifier: Modifier = Modifier,
    mWebViewClient: WebViewClient,
    webViewSettings: (WebView.() -> Unit)? = null
) {
    // Tracks the last URL loaded to prevent unnecessary reloads
    var lastLoadedUrl by remember { mutableStateOf<String?>(null) }

    // Stores the reference to the actual WebView instance
    var webViewRef by remember { mutableStateOf<WebView?>(null) }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                webViewClient = mWebViewClient
                // Apply provided settings (JS, DOM, etc.)
                webViewSettings?.invoke(this)
                lastLoadedUrl = url
                loadUrl(url)
                webViewRef = this
            }
        },
        update = { webView ->
            // Reload only if the new URL differs from the last loaded one
            if (lastLoadedUrl != url) {
                webView.loadUrl(url)
                lastLoadedUrl = url
            }
        }
    )
}