package com.android.blinxapp.dashboard.ui.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.blinxapp.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun DashboardTopBar() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding( start = 16.dp, end = 16.dp)
        ) {
            val ( notificationIcon, profileIcon,supportIcon) = createRefs()

            Box(
                modifier = Modifier
                    .constrainAs(notificationIcon) {
                        start.linkTo(parent.start)
                    }
            ) {
                GlideImage(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                        .width(35.dp)
                        .height(35.dp)
                        .clip(CircleShape)
                        .clickable(enabled = true, onClick = {  }),
                    imageModel = { "https://avatars.githubusercontent.com/u/27887884?v=4" },
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop)
                    )
            }


            Box(
                modifier = Modifier
                    .constrainAs(profileIcon) {
                        end.linkTo(parent.end)
                    }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_help),
                    modifier = Modifier
                        .size(35.dp),
                    contentDescription = "profile icon",
                )

            }
        }
    }
}