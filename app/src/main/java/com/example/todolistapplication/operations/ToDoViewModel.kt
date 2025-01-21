package com.example.todolistapplication.operations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todolistapplication.data.ToDoDao
import com.example.todolistapplication.data.ToDoDatabase
import com.example.todolistapplication.data.ToDoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val toDoDao: ToDoDao

    init {
        val database = Room.databaseBuilder(
            application,
            ToDoDatabase::class.java,
            "todo_database"
        ).build()
        toDoDao = database.toDoDao()
    }

    val toDos: Flow<List<ToDoItem>> = toDoDao.getAllToDos()

    fun addOrUpdateToDo(toDoItem: ToDoItem) {
        viewModelScope.launch {
            toDoDao.insertToDo(toDoItem)
        }
    }

    fun deleteToDo(toDoItem: ToDoItem) {
        viewModelScope.launch {
            toDoDao.deleteToDo(toDoItem)
        }
    }
}