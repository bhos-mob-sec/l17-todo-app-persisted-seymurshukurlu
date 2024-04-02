package az.edu.bhos.l14todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import az.edu.bhos.l14todoapp.data.dto.TodoLocalDto

@Database(entities = [TodoLocalDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}