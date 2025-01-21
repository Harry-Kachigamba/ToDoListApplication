package com.example.todolistapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoItem::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}