package com.example.kalendo.ui.component.coursescreen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kalendo.R
import com.example.kalendo.ui.theme.defaultColor
import com.example.kalendo.ui.viewmodel.CourseViewModel
import com.example.kalendo.util.ColorItem
import com.example.kalendo.util.CourseColorLabel


@Composable
fun FullScreenDialogAddCourse(viewModel: CourseViewModel = hiltViewModel(), onDismiss: () -> Unit) {

    // For the first Outlined Text Field
    var courseTitle by remember { mutableStateOf("") } // Item to be sent to database
    // For the second Outlined Text Field
    val focusRequester = remember { FocusRequester() }
    var color by remember { mutableStateOf<Color?>(defaultColor) }
    var dialogVisible by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<ColorItem?>(null) } // color value to be send to database
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

        // Used for proper ui interaction for the second Outlined Text Field
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


                Header(
                    onDismiss = onDismiss,
                    onClickSave = {
                        if (courseTitle.length > 15) {
                            showAlertDialog = true
                            showAlertMessage = "Course Title must be less than 15 characters"
                        } else if (courseTitle == "") {
                            showAlertDialog = true
                            showAlertMessage = "Course Title cannot be empty"
                        } else if (selectedItem == null) {
                            showAlertDialog = true
                            showAlertMessage = "Please select a Color Label"
                        } else {
                            //save to room database and show a toast message then dismiss
                            //insertCourse = true
                            viewModel.addCourse(courseTitle, selectedItem!!.color)
                            Toast.makeText(context, "Added Course Successfully", Toast.LENGTH_SHORT)
                                .show()
                            onDismiss()
                        }
                    }
                )

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.weight(1f))

                // Body Part
                BodyCourseTitle(
                    courseTitle = courseTitle,
                    onValueChange = {newTitle -> courseTitle = newTitle}
                )

                Spacer(modifier = Modifier.weight(0.5f))

                BodyCourseColorLabel(
                    selectedItem = selectedItem,
                    color = color,
                    focusRequester = focusRequester,
                    onClickTrailingIcon = {dialogVisible = true} ,
                    onFocusChanged = { focusState ->
                        secondTextFieldFocused = focusState
                    }

                )

                BodyCourseColorLabelDialog(
                    dialogVisible = dialogVisible,
                    focusRequester = focusRequester,
                    onDismiss = {
                        dialogVisible = false;
                        focusManager.clearFocus()
                    },
                    onItemClick = { item ->
                        selectedItem = item
                        color = item.color
                    },
                )

                Spacer(modifier = Modifier.weight(10f))




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

@Composable
private fun Header(
    onDismiss: () -> Unit,
    onClickSave: ()  -> Unit

){
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
            text = "Add Course",
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.weight(10f))
        Button(
            onClick = {onClickSave()},
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

}

@Composable
private fun BodyCourseTitle(
    courseTitle: String,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(
        value = courseTitle,
        onValueChange = onValueChange,
        label = {
            Text(
                text = "Course Title",
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
}


@Composable
private fun BodyCourseColorLabel(
    selectedItem: ColorItem?,
    color: Color?,
    focusRequester: FocusRequester,
    onClickTrailingIcon: () -> Unit,
    onFocusChanged: (Boolean) -> Unit
){
    OutlinedTextField(
        value = selectedItem?.name ?: "",
        onValueChange = { },
        label = {
            Text(
                text = "Select a Color Label",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Light
            )
        },
        leadingIcon = {
            color?.let {
                Icon(
                    painter = painterResource(id = R.drawable.course_label),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = it
                )
            }
        },
        trailingIcon = {
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Icon",
                Modifier.clickable { onClickTrailingIcon() }
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
                onFocusChanged(focusState.isFocused)
            }
    )
}


@Composable
private fun BodyCourseColorLabelDialog(
    dialogVisible: Boolean,
    focusRequester: FocusRequester,
    onDismiss: () -> Unit,
    onItemClick: (ColorItem) -> Unit,
){

    if (dialogVisible) {
        focusRequester.requestFocus()
        Dialog(
            onDismissRequest = {onDismiss()}
        ) {
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
                        text = "Select a Color",
                        modifier = Modifier.padding(bottom = 16.dp),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CourseColorLabel.colorItems.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onItemClick(item)
                                    onDismiss()
                                }
                                .padding(vertical = 8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.course_label),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(14.dp)
                                    .align(Alignment.CenterVertically),
                                tint = item.color
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = item.name)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {onDismiss()},
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }



}










