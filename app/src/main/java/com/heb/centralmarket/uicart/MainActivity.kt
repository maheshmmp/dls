/*
 * Created by Mahesh Mathew Paul on 12/12/24, 12:20 pm
 * mahesh.paul@ust.com
 * Last modified 10/12/24, 2:43 pm
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICSystemThemePreference
import com.heb.centralmarket.uicart.themesystem.UICThemeSystem
import com.heb.centralmarket.uicart.themesystem.UICartThemeSystem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UICartThemeSystem.initialize(
            themeSystem = UICThemeSystem.CentralMarket,
            darkMode = UICSystemThemePreference.System,
        )

        setContent {
            UICAppTheme {
                MainScreen()
            }
        }
    }
}
