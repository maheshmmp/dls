/*
 *
 *  Created by Mahesh Paul on 1/22/26, 8:32 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/22/26, 2:34 PM
 *
 */

package com.heb.centralmarket.uicart.icons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.themesystem.UICSize
import com.heb.centralmarket.uicart.themesystem.UICTheme

@Composable
fun CoreIcon(
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    size: Dp = UICSize.size.s16,
    tint: Color = UICTheme.colorScheme.brand.primary.core,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier.size(size),
        tint = tint,
    )
}

@Composable
fun chevronRight(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_chevron_right)

@Composable
fun chevronLeft(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_chevron_left)

@Composable
fun accountAvatar(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_account_avatar)

@Composable
fun trashIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_trash)

@Composable
fun plusIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_plus)

@Composable
fun settingsGear(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_settings_gear)

@Composable
fun creditCard(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_credit_card)

@Composable
fun infoIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_info)

@Composable
fun circleInfoIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_circle_info)

@Composable
fun notificationIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_notification)

@Composable
fun homeIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_home)

@Composable
fun listsIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_lists)

@Composable
fun tabModal(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_tab)

@Composable
fun closeIconModal(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_close)

@Composable
fun cautionIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_caution)

@Composable
fun notificationsOffIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_img_notifications_off)

@Composable
fun backArrow(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_arrow_left)

@Composable
fun truck(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_truck)

@Composable
fun shop(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_shop)

@Composable
fun chef(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_chef)

@Composable
fun suitcase(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_suitcase)

@Composable
fun newspaper(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_newspaper)

@Composable
fun pin(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_pin)

@Composable
fun pen(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_pen)

@Composable
fun search(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_magnifying_glass)

@Composable
fun barcodeRead(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_barcode_read)

@Composable
fun closeVector(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_xclear)

@Composable
fun downArrow(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_chevron_down)

@Composable
fun successVector(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_success)

@Composable
fun errorVector(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_error)

@Composable
fun infoMessage(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_info_message)

@Composable
fun clock(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_clock)

@Composable
fun check(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_check)

@Composable
fun addOn(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_add_on)

@Composable
fun orderAheadCart(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_order_ahead_cart)

@Composable
fun present(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_present)

@Composable
fun locationArrow(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_location_arrow)

@Composable
fun fastCart(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_fast_cart)

@Composable
fun lightning(): ImageVector = ImageVector.vectorResource(id = R.drawable.uic_ic_lightning)
