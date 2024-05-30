package com.example.kalendo.ui.component.coursescreen

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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.kalendo.R


@Composable
fun FullScreenDialogCourse(onDismiss: () -> Unit) {
    var text by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(0.dp) // No rounded corners
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
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

//                    TextButton(
//                        onClick = { /* Handle button click */ },
//                        modifier = Modifier.align(Alignment.CenterVertically).padding(12.dp)
//                    ) {
//                        Text(
//                            text = "Save",
//                            fontSize = 15.sp,
//                            color = MaterialTheme.colorScheme.secondary,
//                            fontWeight = FontWeight.SemiBold
//                        )
//                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 1.dp),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )

                //Spacer(modifier = Modifier.height(56.dp))

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




                Spacer(modifier = Modifier.height(16.dp))


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FullScreenDialogPreview() {
    FullScreenDialogCourse(onDismiss = {})
}