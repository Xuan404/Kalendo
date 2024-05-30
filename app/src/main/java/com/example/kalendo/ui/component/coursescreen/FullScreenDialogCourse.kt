package com.example.kalendo.ui.component.coursescreen

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.kalendo.R
import com.example.kalendo.ui.theme.defaultColor
import com.example.kalendo.util.ColorItem
import com.example.kalendo.util.CourseColorLabel


@Composable
fun FullScreenDialogCourse(onDismiss: () -> Unit) {

    var text by remember { mutableStateOf("") }

    var color by remember { mutableStateOf<Color?>(null) }

    val focusRequester = remember { FocusRequester() }

    var dialogVisible by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<ColorItem?>(null) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {

        // Used for proper ui interaction for the second Outlined Text Field
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
                        text = "Add Course",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.weight(10f))
                    Button(
                        onClick = {
                            // Handle the text submission
                            onDismiss()
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

                // Body Part
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
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
                        .padding(25.dp)
                )



















                OutlinedTextField(
                    value = color?.toString() ?: "", //
                    onValueChange = { },
                    label = {
                        Text(
                            text = "Select a Color Label",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Light
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.course_label),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = defaultColor
                        )
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
                        .padding(25.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            secondTextFieldFocused = focusState.isFocused
                        }
                )




                if (dialogVisible) {
                    Dialog(onDismissRequest = {
                        dialogVisible = false;
                        focusManager.clearFocus()
                    }) {

                        if (dialogVisible) {
                            focusRequester.requestFocus()
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Select a Color",
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                                CourseColorLabel.colorItems.forEach { item ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                selectedItem = item
                                                dialogVisible = false
                                            }
                                            .padding(vertical = 8.dp)
                                    ) {
//                                        Box( //
//                                            modifier = Modifier
//                                                .size(24.dp)
//                                                .background(
//                                                    item.color,
//                                                    shape = RoundedCornerShape(4.dp)
//                                                )
//                                        )
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
                                    onClick = {
                                        dialogVisible = false
                                        focusManager.clearFocus() // Might not need this
                                    },
                                    modifier = Modifier.align(Alignment.End)
                                ) {
                                    Text("Close")
                                }
                            }
                        }
                    }
                }




            }
        }
    }
}


















@Preview(showBackground = true)
@Composable
fun FullScreenDialogPreview() {
    FullScreenDialogCourse(onDismiss = {})
}














