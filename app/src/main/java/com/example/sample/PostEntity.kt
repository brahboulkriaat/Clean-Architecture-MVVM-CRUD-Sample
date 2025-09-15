package com.example.sample

import androidx.room.Entity
import androidx.room.PrimaryKey

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

fun Post.toEntity() = PostEntity(
    userId = userId,
    id = id,
    title = title,
    body = body
)