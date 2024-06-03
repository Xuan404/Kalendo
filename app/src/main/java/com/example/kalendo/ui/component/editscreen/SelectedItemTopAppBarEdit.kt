package com.example.kalendo.ui.component.editscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalendo.R
import com.example.kalendo.ui.theme.KalendoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedItemTopAppBarEdit(
    selectedItemCount: Int,
    onClearSelection: () -> Unit,
    onDeleteSelected: () -> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        title = {
            Text(
                text = "$selectedItemCount" ,
                modifier = Modifier.padding(10.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onClearSelection() }) {
                Icon(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "Cancel Button",
                    modifier = Modifier.padding(12.dp),
                )
            }
        },
        actions = {
            IconButton(onClick = { onDeleteSelected() }) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Delete Course",
                    modifier = Modifier.padding(8.dp),
                )
            }

        }
    )


}

@Preview(showBackground = true)
@Composable
fun MySelectedItemTopAppBarEditPreview() {
    KalendoTheme {
        SelectedItemTopAppBarEdit(
            selectedItemCount = 5,
            onClearSelection = { Unit },
            onDeleteSelected = { Unit }

        )
    }
}
