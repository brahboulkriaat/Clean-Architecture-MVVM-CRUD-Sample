package com.example.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.domain.model.Post

@Entity(tableName = "Posts")
class PostEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    fun toPost() = Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}