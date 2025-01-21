package com.example.todolistapplication.data

import androidx.room.RoomDatabase

abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}