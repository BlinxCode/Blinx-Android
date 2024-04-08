package com.android.blinxapp.feature.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.containerColorWhite
import com.android.blinxapp.ui.theme.primaryGreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomCardWithText(buttonClicked: MutableState<Boolean>,placeHolder:String,  showIcon: Boolean, isCopy: Boolean = false) {
    Card(
        onClick = {
            buttonClicked.value = true
        },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxSize()
                .background(containerColorWhite)
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .safeContentPadding()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                val (text,copy, arrow) = createRefs()

                Text(
                    style = Typography.labelSmall,
                    text = placeHolder,
                    modifier = Modifier
                        .padding(top = 5.dp, start = 5.dp)
                        .constrainAs(text) {
                            start.linkTo(parent.start)
                        }
                )

                if (showIcon){
                    Icon(
                        imageVector = Icons.Filled.ExpandMore,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .constrainAs(arrow) {
                                end.linkTo(parent.end)
                            }
                            .size(25.dp),
                        contentDescription = "drawable icons",
                    )
                }

                if(isCopy){

                    Text(
                        style = Typography.labelSmall,
                        text = "Copy",
                        color = primaryGreen,
                        modifier = Modifier
                            .padding(top = 5.dp, end = 5.dp)
                            .constrainAs(copy) {
                                end.linkTo(arrow.start)
                            }
                    )

                    Icon(
                        imageVector = Icons.Filled.CopyAll,
                        tint = primaryGreen,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .constrainAs(arrow) {
                                end.linkTo(parent.end)
                            }
                            .size(25.dp),
                        contentDescription = "drawable icons",
                    )
                }

            }
        }
    }
}

