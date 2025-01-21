package com.example.todolistapplication.operations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapplication.data.ToDoItem

@Composable
fun ToDoListScreen(
    viewModel: ToDoViewModel = viewModel(),
    onNavigateToAddEdit: (ToDoItem?) -> Unit
) {
    val toDoList = viewModel.toDos.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToAddEdit(null) }) {
                Text("+")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(toDoList.value) { toDo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(toDo.title, style = MaterialTheme.typography.titleLarge)
                        Text(toDo.description)
                    }
                    IconButton(onClick = { onNavigateToAddEdit(toDo) }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { viewModel.deleteToDo(toDo) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}