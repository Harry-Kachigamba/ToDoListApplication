package com.example.todolistapplication.operations

open class Routes(val route: String) {
    object ToDoList : Routes("list")
    object AddEditToDo : Routes("addEdit?todoId={todoId}") {
        fun createRoute(todoId: Int?) = "addEdit?todoId=${todoId ?: -1}"
    }
}