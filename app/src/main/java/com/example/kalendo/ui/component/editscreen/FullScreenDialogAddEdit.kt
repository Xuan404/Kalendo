package com.example.kalendo.ui.component.editscreen

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
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
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.ui.viewmodel.AssignmentViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FullScreenDialogAddEdit(
    viewModel: AssignmentViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    course: CourseModel?
) {

    // For the Component Title Outlined Text Field
    var componentTitle by remember { mutableStateOf("") } // Item to be sent to database
    // For the Component Type Outlined Text Field
    val focusRequester = remember { FocusRequester() }
    var dialogVisible by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var selectedItemIsDeadline by remember { mutableStateOf<Boolean?>(null)}
    // For Time picker
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf(LocalTime.now()) }
    // For date picker
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
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
        var componentTypeTextFieldFocused by remember { mutableStateOf(false) }
        LaunchedEffect(componentTypeTextFieldFocused) {
            if (componentTypeTextFieldFocused) {
                dialogVisible = true
            }
        }
        var datePickerTextFieldFocused by remember { mutableStateOf(false) }
        LaunchedEffect(datePickerTextFieldFocused) {
            if (datePickerTextFieldFocused) {
                showDatePicker = true
            }
        }
        var timePickerTextFieldFocused by remember { mutableStateOf(false) }
        LaunchedEffect(timePickerTextFieldFocused) {
            if (timePickerTextFieldFocused) {
                showTimePicker = true
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
                Header(
                    onDismiss = onDismiss,
                    onClickSave = {
                        if (componentTitle.length > 20) {
                            showAlertDialog = true
                            showAlertMessage = "ComponentTitle must be less than 20 characters"
                        } else if (componentTitle == "") {
                            showAlertDialog = true
                            showAlertMessage = "Component Title cannot be empty"
                        } else if (selectedItemIsDeadline == null) {
                            showAlertDialog = true
                            showAlertMessage = "Please select a Component Type"
                        } else {
                            //save to room database and show a toast message then dismiss
                            //insertCourse = true
                            Log.i("CourseEdit", course.toString())
                            if (course != null) {
                                viewModel.addAssignment(
                                    courseId = course.id,
                                    title = componentTitle,
                                    date = selectedDate,
                                    time = selectedTime,
                                    isDeadline = selectedItemIsDeadline!!,
                                    isCompleted = false
                                )
                            }
                            Toast.makeText(context, "Added Component Successfully", Toast.LENGTH_SHORT)
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
                BodyComponentTitle(
                    componentTitle = componentTitle,
                    onValueChange = {newTitle -> componentTitle = newTitle}
                )

                Spacer(modifier = Modifier.weight(0.5f))

                // Date and Time
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                ){

                    OutlinedTextField(
                        value = selectedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Date",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Light
                            )
                        },
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_calender),
                                contentDescription = "Date Picker",
                                modifier = Modifier.size(20.dp),
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.onBackground
                        ),
                        modifier = Modifier
                            .clickable { showDatePicker = true }
                            .weight(1f)
                            .focusRequester(focusRequester)
                            .onFocusChanged { focusState ->
                                datePickerTextFieldFocused = focusState.isFocused
                            }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    OutlinedTextField(
                        value = selectedTime.format(DateTimeFormatter.ofPattern("h:mm a")),
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Time",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Light
                            )
                        },
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_clock),
                                contentDescription = "Time Picker",
                                modifier = Modifier.size(20.dp),
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.onBackground
                        ),
                        modifier = Modifier
                            .clickable { showTimePicker = true }
                            .weight(0.9f)
                            .focusRequester(focusRequester)
                            .onFocusChanged { focusState ->
                                timePickerTextFieldFocused = focusState.isFocused
                            }
                    )


                }

                Spacer(modifier = Modifier.weight(0.5f))

                BodyCourseComponentType(
                    selectedItem = selectedItem,
                    focusRequester = focusRequester,
                    leadingIcon = {
                        if (selectedItemIsDeadline == false) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_to_do),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                            )
                        } else if (selectedItemIsDeadline == true) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_deadline),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                            )
                        }
                    },
                    onClickTrailingIcon = {dialogVisible = true},
                    onFocusChanged = { focusState ->
                        componentTypeTextFieldFocused = focusState
                    }
                )

                Spacer(modifier = Modifier.weight(10f))

                // Show Component type picker dialog
                if (dialogVisible) {
                    focusRequester.requestFocus()
                    BodyCourseComponentTypeDialog(
                        onDismiss = {
                            dialogVisible = false;
                            focusManager.clearFocus()
                        },
                        onItemClickToDo = {
                            selectedItem = "To-Do"
                            selectedItemIsDeadline = false

                        },
                        onItemClickDeadline = {
                            selectedItem = "Deadline"
                            selectedItemIsDeadline = true
                        }
                    )
                }

                // Show Date Picker Dialog
                if (showDatePicker) {
                    BodyCourseComponentDatePicker(
                        onDismiss = {
                            showDatePicker = false
                            focusManager.clearFocus()
                        },
                        onDateSelected = {newDate ->
                            selectedDate = newDate
                        },
                        context = context
                    )
                }

                // Show Time Picker Dialog
                if (showTimePicker) {
                    BodyCourseComponentTimePicker(
                        onDismiss = {
                            showTimePicker = false
                            focusManager.clearFocus()
                        },
                        onTimeSelected = { newTime ->
                            selectedTime = newTime
                        }

                    )
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
        }
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 6.dp)
    ) {
        IconButton(onClick = onDismiss) {
            Icon(
                painter = painterResource(id = R.drawable.icon_cancel),
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
private fun BodyComponentTitle(
    componentTitle: String,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(
        value = componentTitle,
        onValueChange = onValueChange,
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
}

@Composable
private fun BodyCourseComponentType(
    selectedItem: String,
    focusRequester: FocusRequester,
    leadingIcon: @Composable () -> Unit,
    onClickTrailingIcon: () -> Unit,
    onFocusChanged: (Boolean) -> Unit
){
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
            leadingIcon()
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
private fun BodyCourseComponentTypeDialog(
    onDismiss: () -> Unit,
    onItemClickToDo: () -> Unit,
    onItemClickDeadline: () -> Unit
){
    Dialog(
        onDismissRequest = { onDismiss() }
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
                            onItemClickToDo()
                            onDismiss()
                        }
                        .padding(vertical = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_to_do),
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
                            onItemClickDeadline()
                            onDismiss()
                        }
                        .padding(vertical = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_deadline),
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
                    onClick = { onDismiss() },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Cancel")
                }
            }
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BodyCourseComponentDatePicker(
    onDismiss: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    context: Context
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = {
                    val selectedDateMillis = datePickerState.selectedDateMillis
                    if (selectedDateMillis != null) {
                        val selectedDate = Instant.ofEpochMilli(selectedDateMillis).atZone(ZoneId.of("UTC")).toLocalDate()
                        onDateSelected(selectedDate)
                        onDismiss()
                    } else {
                        Toast.makeText(context, "Please Select a date", Toast.LENGTH_SHORT).show()
                    }
                },
            ) {
                Text(
                    text = "OK",
                    color = MaterialTheme.colorScheme.secondary
                )
            }

        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(
                    text = "Cancel",
                    color = MaterialTheme.colorScheme.secondary
                )
            }

        },
        colors = DatePickerDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false,
            colors = DatePickerDefaults.colors(
                todayDateBorderColor = MaterialTheme.colorScheme.secondary,
                todayContentColor = MaterialTheme.colorScheme.onPrimary,
                selectedDayContainerColor = MaterialTheme.colorScheme.secondary,
                selectedDayContentColor = MaterialTheme.colorScheme.onSecondary,
                selectedYearContainerColor = MaterialTheme.colorScheme.secondary,
                selectedYearContentColor = MaterialTheme.colorScheme.onSecondary,
                currentYearContentColor = MaterialTheme.colorScheme.onPrimary,
            )

        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BodyCourseComponentTimePicker(
    onDismiss: () -> Unit,
    onTimeSelected: (LocalTime) -> Unit,
){
    val timePickerState = rememberTimePickerState()

    TimePickerDialog(
        onDismiss = { onDismiss() },
        onConfirm = {
            val cal = Calendar.getInstance()
            cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
            cal.set(Calendar.MINUTE, timePickerState.minute)
            cal.isLenient = false

            var selectedTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
            onTimeSelected(selectedTime)
            onDismiss()
        },
    ) {
        TimeInput(state = timePickerState)
    }
}


// FROM: https://github.com/Dinesh2510/Jetpack-Compose-UI-Components-Material-3/blob/main/TimePicker.kt#L90
@Composable
fun TimePickerDialog(
    title: String = "Enter Time",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = {onDismiss()},
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    //style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = {onDismiss()}) {
                        Text(
                            text = "Cancel",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    TextButton(onClick = {onConfirm()}) {
                        Text(
                            text = "OK",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}
