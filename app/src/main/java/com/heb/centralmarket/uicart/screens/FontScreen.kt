/*
 * Created by Mahesh Mathew Paul on 12/12/24, 12:21 pm
 * mahesh.paul@ust.com
 * Last modified 12/12/24, 12:20 pm
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.previews.AllScreenPreview
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun FontScreen(drawerState: DrawerState) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.font_title,
                drawerState = drawerState,
            )
        },
    ) {
        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Heading1",
                    style = UICTypography.typography.heading.h1,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Heading2",
                    style = UICTypography.typography.heading.h2,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Heading3",
                    style = UICTypography.typography.heading.h3,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Heading4",
                    style = UICTypography.typography.heading.h4,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Heading5",
                    style = UICTypography.typography.heading.h5,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Heading6",
                    style = UICTypography.typography.heading.h6,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Title1",
                    style = UICTypography.typography.title.t1,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Title2",
                    style = UICTypography.typography.title.t2,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Subtitle",
                    style = UICTypography.typography.subtitle.subTitle.regular,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Subtitle Strikethrough",
                    style = UICTypography.typography.subtitle.subTitle.strikeThrough,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Subtitle Bold",
                    style = UICTypography.typography.subtitle.subTitle.bold,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Subtitle Bold Strikethrough",
                    style = UICTypography.typography.subtitle.subTitle.boldStrikeThrough,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Large",
                    style = UICTypography.typography.body.body1.regular,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Large Strike",
                    style = UICTypography.typography.body.body1.strikeThrough,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Large Bold",
                    style = UICTypography.typography.body.body1.bold,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Medium",
                    style = UICTypography.typography.body.body2.regular,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Medium Strike",
                    style = UICTypography.typography.body.body2.strikeThrough,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Medium Bold",
                    style = UICTypography.typography.body.body2.bold,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Medium Bold Strike",
                    style = UICTypography.typography.body.body2.boldStrikeThrough,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Small",
                    style = UICTypography.typography.body.body2.regular,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Small Strike",
                    style = UICTypography.typography.body.body2.strikeThrough,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Small Bold",
                    style = UICTypography.typography.body.body2.bold,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Body Small Bold Strike",
                    style = UICTypography.typography.body.body2.boldStrikeThrough,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Caption",
                    style = UICTypography.typography.caption.regular,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Caption Strike",
                    style = UICTypography.typography.caption.strikeThrough,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Caption Bold",
                    style = UICTypography.typography.caption.bold,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Caption Bold Strike",
                    style = UICTypography.typography.caption.boldStrikeThrough,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Footnote",
                    style = UICTypography.typography.footnote.footnote.regular,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = "Footnote Bold",
                    style = UICTypography.typography.footnote.footnote.bold,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )
            }
        }
    }
}

@AllScreenPreview
@Composable
fun FontScreenPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    UICAppTheme {
        FontScreen(drawerState)
    }
}
