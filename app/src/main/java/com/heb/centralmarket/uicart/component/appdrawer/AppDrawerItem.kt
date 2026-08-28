package com.heb.centralmarket.uicart.component.appdrawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.DrawerParams
import com.heb.centralmarket.uicart.MainNavOption
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun <T> AppDrawerItem(
    item: AppDrawerItemInfo<T>,
    onClick: (options: T) -> Unit,
) = Surface(
    color = UICTheme.colorScheme.brand.primary.bg,
    modifier = Modifier.width(240.dp),
    onClick = { onClick(item.drawerOption) },
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier.padding(8.dp),
    ) {
        Icon(
            painter = painterResource(id = item.drawableId),
            contentDescription = stringResource(id = item.descriptionId),
            modifier =
                Modifier.size(24.dp),
            tint = UICTheme.colorScheme.txt.primary,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = item.title),
            style = UICTypography.typography.body.body2.bold,
            textAlign = TextAlign.Center,
            color = UICTheme.colorScheme.txt.primary,
        )
    }
}

class MainStateProvider : PreviewParameterProvider<AppDrawerItemInfo<MainNavOption>> {
    override val values =
        sequence {
            DrawerParams.drawerButtons.forEach { element ->
                yield(element)
            }
        }
}

@Preview
@Composable
fun AppDrawerItemPreview(
    @PreviewParameter(MainStateProvider::class) state: AppDrawerItemInfo<MainNavOption>,
) {
    UICAppTheme {
        AppDrawerItem(item = state, onClick = {})
    }
}
