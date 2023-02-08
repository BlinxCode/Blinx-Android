package com.android.blinxapp.dashboard.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.common.CardTitleColors
import com.android.blinxapp.common.cardColors
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.secondaryGrey

@Composable
fun DashboardCard(painterResource: Painter, title: String, message: String) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),

        shape = RoundedCornerShape(15.dp),

        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .safeContentPadding()
                .background(cardColors()),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(

                painter = painterResource,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(40.dp)
                    .align(Alignment.CenterVertically),
                contentDescription = "drawable icons",
            )

            Column(
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 20.dp, bottom = 20.dp)
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
            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically),
                contentDescription = "drawable icons",
            )
        }
    }
}