package com.android.blinxapp.ui.feature.presentation.components.common.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.ui.theme.containerColorBlack
import com.android.blinxapp.ui.theme.grey
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun ProfileImage(imgStr: String, modifier: Modifier = Modifier) {
    GlideImage(
        modifier = modifier
            .size(30.dp)
            .clip(CircleShape)
            .clickable(enabled = true, onClick = { }),
        imageModel = { imgStr },
        // shows an image with a circular revealed animation.
        // shows a placeholder ImageBitmap when loading.

        previewPlaceholder = R.drawable.profile_placeholder,
        component = rememberImageComponent {
            // shows a shimmering effect when loading an image.
            +ShimmerPlugin(
                baseColor = containerColorBlack,
                highlightColor = grey
            )
        },

        imageOptions = ImageOptions(
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            requestSize = IntSize(30, 30),
            alpha = 10F,
            alignment = Alignment.Center
        ),
        failure = {
            Image(
                painter = painterResource(R.drawable.profile_placeholder),
                contentDescription = "avatar",
                // crop the image if it's not a square
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    // clip to the circle shape
                    .clip(CircleShape)
            )
        }

    )
}