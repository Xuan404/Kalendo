package com.example.kalendo.ui.component.editscreen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kalendo.R
import com.example.kalendo.ui.theme.defaultColor
import com.example.kalendo.ui.viewmodel.AssignmentViewModel
import com.example.kalendo.ui.viewmodel.CourseViewModel
import com.example.kalendo.util.ColorItem
import com.example.kalendo.util.CourseColorLabel

@Composable
fun FullScreenDialogAddEdit(viewModel: AssignmentViewModel = hiltViewModel(), onDismiss: () -> Unit) {

    // For the first Outlined Text Field
    var courseTitle by remember { mutableStateOf("") } // Item to be sent to database
    // For the Component Item Outlined Text Field
    val focusRequester = remember { FocusRequester() }
    var dialogVisible by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var selectedItemIsDeadline by remember { mutableStateOf<Any?>(null)}
    // Invalid Input Alert Dialog
    var showAlertDialog by remember { mutableStateOf(false) }
    var showAlertMessage by remember { mutableStateOf("") }
    // Toast message after successfull ROOM insert
    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {

        // Used for proper ui interaction for the Component Item Outlined Text Field
        // These variables need to be inside the dialog func otherwise it won't work
        val focusManager = LocalFocusManager.current
        var secondTextFieldFocused by remember { mutableStateOf(false) }
        LaunchedEffect(secondTextFieldFocused) {
            if (secondTextFieldFocused) {
                dialogVisible = true
            }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(0.dp) // No rounded corners
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        onClick = { focusManager.clearFocus() },
                        indication = null, // Remove the click animation
                        interactionSource = remember { MutableInteractionSource() }
                    )
            ) {
                // Top Part
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 6.dp)
                ) {
                    IconButton(onClick = onDismiss) {
                        Icon(
                            painter = painterResource(id = R.drawable.cancel),
                            contentDescription = "Cancel Dialog",
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Add Component",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.weight(10f))
                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(12.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(
                            text = "Save",
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.weight(1f))

                // Body Part
                OutlinedTextField(
                    value = courseTitle,
                    onValueChange = { courseTitle = it },
                    label = {
                        Text(
                            text = "Component Title",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Light
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                )

                Spacer(modifier = Modifier.weight(0.5f))














                OutlinedTextField(
                    value = selectedItem, //
                    onValueChange = { },
                    label = {
                        Text(
                            text = "Select Component Type",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Light
                        )
                    },
                    leadingIcon = {
                        if (selectedItemIsDeadline == false) {
                            Icon(
                                painter = painterResource(id = R.drawable.to_do),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                            )
                        } else if (selectedItemIsDeadline == true) {
                            Icon(
                                painter = painterResource(id = R.drawable.deadline),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                            )
                        }
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown Icon",
                            Modifier.clickable { dialogVisible = true }
                        )
                    },
                    readOnly = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            secondTextFieldFocused = focusState.isFocused
                        }
                )
                Spacer(modifier = Modifier.weight(10f))

                if (dialogVisible) {
                    Dialog(onDismissRequest = {
                        dialogVisible = false;
                        focusManager.clearFocus()
                    }) {

                        if (dialogVisible) {
                            focusRequester.requestFocus()
                        }

                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Select Type",
                                    modifier = Modifier.padding(bottom = 16.dp),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedItem = "To-Do"
                                            selectedItemIsDeadline = false
                                            focusManager.clearFocus()
                                            dialogVisible = false
                                        }
                                        .padding(vertical = 8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.to_do),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(14.dp)
                                            .align(Alignment.CenterVertically),
                                        //tint = item.color
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "To-Do")

                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedItem = "Deadline"
                                            selectedItemIsDeadline = true
                                            focusManager.clearFocus()
                                            dialogVisible = false
                                        }
                                        .padding(vertical = 8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.deadline),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(14.dp)
                                            .align(Alignment.CenterVertically),
                                        //tint = item.color
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "Deadline")
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = {
                                        dialogVisible = false
                                        focusManager.clearFocus() // Might not need this
                                    },
                                    modifier = Modifier.align(Alignment.End)
                                ) {
                                    Text("Cancel")
                                }
                            }
                        }
                    }
                }















            }
        }
    }



    // Show AlertDialog
    if (showAlertDialog) {
        InvalidInputAlertDialog(
            alertMessage = showAlertMessage,
            onDismiss = {
                showAlertDialog = false
                showAlertMessage = ""
            }
        )
    }


}






@Composable
private fun InvalidInputAlertDialog(alertMessage: String, onDismiss: () -> Unit){

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "OK",
                    color = MaterialTheme.colorScheme.secondary)
            }
        },
        title = { Text("Invalid Input") },
        text = { Text(alertMessage) },
        properties = DialogProperties(dismissOnClickOutside = true),
        containerColor = MaterialTheme.colorScheme.primary
    )

}


