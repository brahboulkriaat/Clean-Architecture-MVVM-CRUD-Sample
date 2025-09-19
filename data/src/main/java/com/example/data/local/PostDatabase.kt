package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.entity.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1
)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}