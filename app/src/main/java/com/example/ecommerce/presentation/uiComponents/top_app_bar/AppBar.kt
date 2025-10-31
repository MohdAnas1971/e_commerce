package com.example.ecommerce.presentation.uiComponents.top_app_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    onProfileClick: () -> Unit={},
    onMenuClick: () -> Unit = {},
    title: String? = null,
    showProfile: Boolean = true
) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.logoipsum),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(width = 120.dp, height = 36.dp)
                        .padding(horizontal = 8.dp),
                    contentScale = ContentScale.Fit
                )
                title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 8.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onMenuClick,
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Navigation menu",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            if (showProfile) {
                ProfileIcon(
                    onProfileClick = onProfileClick,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
fun ProfileIcon(
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .clickable(
                onClick = onProfileClick,
                //indication = rememberRipple(bounded = true, color =MaterialTheme.colorScheme.error, radius = 8.dp),
               // interactionSource = remember { MutableInteractionSource() }
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = CircleShape
            )
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.profile_logo),
            contentDescription = "Profile",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

// Optional: Alternative with badge/notification indicator
@Composable
fun ProfileIconWithBadge(
    onProfileClick: () -> Unit,
    hasNotifications: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        ProfileIcon(onProfileClick = onProfileClick)

        if (hasNotifications) {
            Badge(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(12.dp)
            ) {
                // Empty badge for notification indicator
            }
        }
    }
}
