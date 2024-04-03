package az.edu.bhos.l14todoapp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import az.edu.bhos.l14todoapp.entities.TodoEntity

// todo complete the entity class
@Entity(tableName = "todos")
data class TodoLocalDto(
    @PrimaryKey
    val id: String,
    val title: String,
    val completed: Boolean,
    val weekday: String
){
    fun toEntity(): TodoEntity {
        return TodoEntity(
            id = this.id,
            title = this.title,
            completed = this.completed,
            weekday = this.weekday
        )
    }
}