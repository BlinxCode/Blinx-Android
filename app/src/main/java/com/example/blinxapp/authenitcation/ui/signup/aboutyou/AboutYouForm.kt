package com.example.blinxapp.authenitcation.ui.signup.aboutyou

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blinxapp.common.outlineColors
import com.example.blinxapp.ui.theme.Typography
import com.example.blinxapp.ui.theme.primaryGreen


@Composable
fun AboutYouForm() {

    // Adding a Spacer of height 20dp
    Spacer(modifier = Modifier.height(25.dp))
    //First name field
    Text(
        text = "First name",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )
    Spacer(Modifier.size(16.dp))
    FirstNameField()
    
    //Middle Name
    Text(
        modifier = Modifier.padding(top = 25.dp),
        text = "Middle name",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )
    Spacer(Modifier.size(16.dp))
    MiddleNameField()

    //Last Name
    Text(
        modifier = Modifier.padding(top = 25.dp),
        text = "Last name",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )
    Spacer(Modifier.size(16.dp))
    LastNameField()

    //Last Name
    Text(
        modifier = Modifier.padding(top = 25.dp),
        text = "Let's create your username",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )
    Spacer(Modifier.size(10.dp))
    Text(
        text = "You can send and receive funds  with your unique username.",
        style = Typography.titleSmall,
        color = primaryGreen,
        textAlign = TextAlign.Start)


    Spacer(Modifier.size(16.dp))
    LastNameField()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstNameField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = outlineColors(),
        value = text,
        onValueChange = { text = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiddleNameField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = outlineColors(),
        value = text,
        onValueChange = { text = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastNameField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = outlineColors(),
        value = text,
        onValueChange = { text = it }
    )
}

