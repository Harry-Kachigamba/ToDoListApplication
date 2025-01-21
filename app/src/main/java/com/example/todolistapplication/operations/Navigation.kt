package com.example.todolistapplication.operations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolistapplication.data.ToDoItem

@Composable
fun Navigation(navController: NavHostController, toDoViewModel: ToDoViewModel = viewModel()) {
    NavHost(navController, startDestination = "list") {
        composable("list") {
            ToDoListScreen(
                onNavigateToAddEdit = { toDo ->
                    navController.navigate("addEdit?todoId=${toDo?.id}")
                }
            )
        }
        composable("addEdit?todoId={todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull()
            val toDoItem = toDoViewModel.getToDoById(todoId)

            AddEditToDoScreen(
                toDoItem = toDoItem,
                onSave = { item ->
                    toDoViewModel.addOrUpdateToDo(item)
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
