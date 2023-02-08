package com.android.blinxapp.common.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.android.blinxapp.R


//TopBar setup
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingTopbar(
    isGettingStarted: Boolean,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    val buttonColors =  TopAppBarDefaults.smallTopAppBarColors(
        containerColor =  MaterialTheme.colorScheme.primary)

    if (!isGettingStarted){
        TopAppBar(
            colors = buttonColors,
            title = {},
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack){
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            }
        )
    }
}