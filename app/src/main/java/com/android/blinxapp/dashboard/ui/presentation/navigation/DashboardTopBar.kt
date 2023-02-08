package com.android.blinxapp.dashboard.ui.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
            val ( notificationIcon, profileIcon,supportIcon) = createRefs()

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
                    .constrainAs(profileIcon) {
                        end.linkTo(supportIcon.start)
                    }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_help),
                    modifier = Modifier
                        .size(35.dp),
                    contentDescription = "profile icon",
                )

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
                    contentDescription = "support icon",
                )

            }

        }
    }
}