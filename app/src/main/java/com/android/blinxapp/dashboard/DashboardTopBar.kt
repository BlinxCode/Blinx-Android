package com.android.blinxapp.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.blinxapp.R
import com.android.blinxapp.dashboard.common.NewMessageAnimation


@Composable
fun DashboardTopBar() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            val ( notificationIcon, supportIcon) = createRefs()

            Box(
                modifier = Modifier
                    .constrainAs(notificationIcon) {
                        start.linkTo(parent.start)
                    }
            ) {
                NewMessageAnimation()
            }


            Box(
                modifier = Modifier
                    .constrainAs(supportIcon) {
                        end.linkTo(parent.end)
                    }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_help),
                    modifier = Modifier
                        .size(35.dp),
                    contentDescription = "drawable icons",
                )

            }

        }
    }
}