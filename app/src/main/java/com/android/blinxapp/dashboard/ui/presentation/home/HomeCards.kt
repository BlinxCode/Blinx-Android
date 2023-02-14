package com.android.blinxapp.dashboard.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.android.blinxapp.R
import com.android.blinxapp.common.CardTitleColors
import com.android.blinxapp.common.YellowColor
import com.android.blinxapp.common.cardColors
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.secondaryGrey
import com.android.blinxapp.ui.theme.white

@Composable
fun AutomateCard(onClicked: () -> Unit, painterResource: Painter, title: String, message: String) {
    ComposeCard(onClicked, painterResource, title, message)
}

@Composable
fun BvnCard(onClicked: () -> Unit, painterResource: Painter, title: String, message: String) {
    ComposeCard(onClicked, painterResource, title, message)
}

@Composable
fun LinkBankCard(onClicked: () -> Unit, painterResource: Painter, title: String, message: String) {
    ComposeCard(onClicked, painterResource, title, message)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeCard(onClicked: () -> Unit, painterResource: Painter, title: String, message: String) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onClicked() }

    ) {
        Box(
            modifier = Modifier.background(cardColors())
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp, end = 10.dp, start = 20.dp)
                    .background(cardColors())
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .safeContentPadding()
                        .padding()
                        .background(cardColors()),
                ) {
                    val (image, column, icon) = createRefs()

                    Image(
                        painter = painterResource,
                        modifier = Modifier
                            .constrainAs(image) {
                                start.linkTo(parent.start)
                            }
                            .size(40.dp),
                        contentDescription = "drawable icons",
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(column) {
                                start.linkTo(image.end, margin = 10.dp)
                                top.linkTo(image.top)
                                bottom.linkTo(image.bottom, margin = 5.dp)
                                end.linkTo(icon.start, margin = 5.dp)
                                width = Dimension.fillToConstraints
                            }
                            .background(cardColors())
                    ) {
                        Text(
                            text = title,
                            color = CardTitleColors(),
                            style = Typography.titleSmall,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = message,
                            color = secondaryGrey,
                            style = Typography.labelSmall,
                            textAlign = TextAlign.Start
                        )
                    }

                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_right),
                        modifier = Modifier
                            .size(30.dp)
                            .constrainAs(icon) {
                                end.linkTo(parent.end)
                                start.linkTo(column.end,  margin = 5.dp)
                                top.linkTo(column.top,  margin = 10.dp)
                                bottom.linkTo(column.bottom)
                            },
                        contentDescription = "drawable icons",
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardComingSoon(
    onClicked: () -> Unit,
    painterResource: Painter,
    title: String,
    message: String
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onClicked() }

    ) {
        Box(
            modifier = Modifier.background(cardColors())
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp, end = 10.dp, start = 20.dp)
                    .background(cardColors())
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .safeContentPadding()
                        .padding()
                        .background(cardColors()),
                ) {
                    val (icon, column, button) = createRefs()

                    Image(
                        painter = painterResource,
                        modifier = Modifier
                            .constrainAs(icon) {
                                start.linkTo(parent.start)
                            }
                            .size(40.dp),
                        contentDescription = "drawable icons",
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(column) {
                                start.linkTo(icon.end, margin = 10.dp)
                                top.linkTo(icon.top)
                                bottom.linkTo(icon.bottom)
                                end.linkTo(button.start, margin = 5.dp)
                                width = Dimension.fillToConstraints
                            }
                            .background(cardColors())
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier,
                            color = CardTitleColors(),
                            style = Typography.titleSmall,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = message,
                            modifier = Modifier,
                            color = secondaryGrey,
                            style = Typography.labelSmall,
                            textAlign = TextAlign.Start
                        )
                    }
                    Button(
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .constrainAs(button) {
                                end.linkTo(parent.end)
                                start.linkTo(column.end)
                            },
                        onClick = { },
                        colors = YellowColor(),
                        shape = RoundedCornerShape(20)
                    ) {

                        Text(
                            style = Typography.labelSmall,
                            text = stringResource(R.string.coming_soon),
                            color = white,
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                        )
                    }
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun CardComingSoonPreview() {
    ComposeCard(
        onClicked = {},
        painterResource(R.drawable.naira),
        stringResource(R.string.no_automation),
        stringResource(R.string.create_automation)
    )

}