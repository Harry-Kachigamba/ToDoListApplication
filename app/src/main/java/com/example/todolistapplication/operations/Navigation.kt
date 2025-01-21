package com.example.todolistapplication.operations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolistapplication.data.ToDoItem

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = "list") {
        composable("list") {
            ToDoListScreen(
                onNavigateToAddEdit = { toDo ->
                    navController.navigate("addEdit?todoId=${toDo?.id}")
                }
            )
        }
        composable("addEdit") {
            AddEditToDoScreen(
                toDoItem = null,
                onSave = { toDoItem ->
                    saveToDoItem(toDoItem)
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack()}
            )
        }
    }
}

fun saveToDoItem(toDoItem: ToDoItem) {
    println("Saving ToDoItem: ${toDoItem.title}, ${toDoItem.description}")
}
