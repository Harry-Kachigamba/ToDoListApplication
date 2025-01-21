package com.example.todolistapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDoDao: ToDoItem)

    @Update
    suspend fun updateToDo(toDo: ToDoItem)

    @Delete
    suspend fun deleteToDo(toDoDao: ToDoItem)

    @Query("SELECT * FROM todo_table ORDER BY id DESC")
    fun getAllToDos(): Flow<List<ToDoItem>>
}