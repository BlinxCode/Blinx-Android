package com.android.blinxapp.common.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.android.blinxapp.R
import com.android.blinxapp.dashboard.DashboardTopBar


//TopBar setup
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    val buttonColors =  TopAppBarDefaults.smallTopAppBarColors(
        containerColor =  MaterialTheme.colorScheme.primary)

        TopAppBar(
            colors = buttonColors,
            title = {},
            modifier = modifier,

            actions = {
                if(!canNavigateBack){
                    DashboardTopBar()
                }
            },
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