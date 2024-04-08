package com.android.blinxapp.feature.presentation.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.blinxapp.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(
    canNavigateBack: Boolean, titleBar: MutableState<String>,
    navigateUp: () -> Unit,
    isDashboard: Boolean,
    isGettingStarted: Boolean
) {

    val buttonColors = topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary
    )


    if (!isGettingStarted){
        TopAppBar(
            colors = buttonColors,
            title = {
//                Text(
//                    text = titleBar.value,
//                    style = MaterialTheme.typography.labelMedium,
//                    textAlign = TextAlign.Center
//                )
            },
            navigationIcon = {

                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }


            },
            actions = {
                if (isDashboard) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(start = 16.dp, end = 16.dp)
                        ) {
                            val (notificationIcon, profileIcon, supportIcon) = createRefs()

                            Box(
                                modifier = Modifier
                                    .constrainAs(notificationIcon) {
                                        start.linkTo(parent.start)
                                    }
                            ) {
                                GlideImage(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .width(35.dp)
                                        .height(35.dp)
                                        .clip(CircleShape)
                                        .clickable(enabled = true, onClick = { }),
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
            }
        )
    }

}