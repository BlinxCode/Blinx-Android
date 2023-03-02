package com.android.blinxapp.dashboard.ui.presentation.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.blinxapp.R
import com.android.blinxapp.common.cardTitleColors
import com.android.blinxapp.common.customviews.ContinueButtonButton
import com.android.blinxapp.common.customviews.PhoneInputField
import com.android.blinxapp.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterialApi::class
)
@Composable

fun DebitCardInfoView(modalBottomSheetState: ModalBottomSheetState, scope: CoroutineScope) {

    val close = {
        scope.launch {
            modalBottomSheetState.hide()
        }
    }


    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        modifier = Modifier.fillMaxSize()
        ,
                sheetContent = {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Text(
                    "Add a debit card to fund wallet",
                    color = cardTitleColors(),
                    style = Typography.titleSmall,
                )

                Text(
                    "card number",
                    color = cardTitleColors(),
                    style = Typography.titleSmall,
                )
                PhoneInputField()

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()) {
                    val ( dateTxt, dateField, cvcText, cvcField) = createRefs()

                    Text("Expiry date",
                        color = cardTitleColors(),
                        style = Typography.titleSmall,
                        modifier = Modifier.constrainAs(dateTxt){
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                    )

                    Box(modifier = Modifier.constrainAs(dateField){
                        top.linkTo(dateTxt.bottom)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }) {
                        PhoneInputField()
                    }

                    Text("CVC number",
                        color = cardTitleColors(),
                        style = Typography.titleSmall,
                        modifier = Modifier.constrainAs(cvcText){
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                    )

                    Box(modifier = Modifier.constrainAs(cvcField){
                        top.linkTo(cvcText.bottom)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }) {
                        PhoneInputField()
                    }

                }

                //Continue button to show cards
                ContinueButtonButton(onProceedClicked ={ close()},
                    stringResource(R.string.continue_txt),
                )
            }
        },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {

        }
    }
}
