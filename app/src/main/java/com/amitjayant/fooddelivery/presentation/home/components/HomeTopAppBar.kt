@file:OptIn(ExperimentalMaterial3Api::class)

package com.amitjayant.fooddelivery.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.amitjayant.fooddelivery.R
import com.amitjayant.fooddelivery.data.util.Dimensions
import com.amitjayant.fooddelivery.presentation.ui.FoodDeliveryTheme

@Composable
fun HomeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    onSignOut: () -> Unit = {}
) {
    var showSignOut by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        modifier = Modifier.padding(start = Dimensions.dimenSmall),
                        text = stringResource(id = R.string.home),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                Text(
                    text = stringResource(id = R.string.sample_address),
                    style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
        },
        actions = {
            IconButton(onClick = { showSignOut = true }) {
                Image(
                    modifier = Modifier.size(Dimensions.dimenLarge),
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
                )
                DropdownMenu(
                    expanded = showSignOut,
                    onDismissRequest = { showSignOut = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.sign_out)) },
                        onClick = onSignOut,
                        leadingIcon = {
                            Icon(
                                Icons.AutoMirrored.Outlined.Logout,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview
@Composable
private fun HomeTopAppBarPreview() {
    FoodDeliveryTheme {
        HomeTopAppBar()
    }
}