package com.example.app.core.di

import android.app.Application
import androidx.room.Room
import com.example.data.local.PostDao
import com.example.data.local.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(/* @ApplicationContext app: Context */app: Application): PostDatabase =
        Room.databaseBuilder(app, PostDatabase::class.java, "posts.db").build()

    @Provides
    fun providePostDao(db: PostDatabase): PostDao = db.postDao()
}