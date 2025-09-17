package com.example.sample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PostEntity::class],
    version = 1
)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}