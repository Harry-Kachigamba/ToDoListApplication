package com.example.todolistapplication.operations

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.todolistapplication.data.ToDoDatabase
import com.example.todolistapplication.data.ToDoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ToDoApplication : Application() {
    val database: ToDoDatabase by lazy {
        Room.databaseBuilder(this, ToDoDatabase::class.java, "todo_database").build()
    }
}

class ToDoViewModel : ViewModel() {
    private val _toDos = MutableStateFlow<List<ToDoItem>>(emptyList())
    val toDos: StateFlow<List<ToDoItem>> = _toDos

    fun addOrUpdateToDo(toDoItem: ToDoItem) {
        val updatedList = _toDos.value.toMutableList().apply {
            val existingIndex = indexOfFirst { it.id == toDoItem.id }
            if (existingIndex >= 0) {
                set(existingIndex, toDoItem)
            } else {
                add(toDoItem)
            }
        }
        _toDos.value = updatedList
    }

    fun deleteToDo(toDoItem: ToDoItem) {
        val updatedList = _toDos.value.toMutableList().apply {
            remove(toDoItem)
        }
        _toDos.value = updatedList
    }

    fun getToDoById(todoId: Int?): ToDoItem? {
        return _toDos.value.find { it.id == todoId }
    }

}