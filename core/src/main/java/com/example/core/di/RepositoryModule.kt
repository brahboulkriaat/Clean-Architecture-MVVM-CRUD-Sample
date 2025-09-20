package com.example.core.di

import com.example.data.local.PostDao
import com.example.data.remote.PostApi
import com.example.data.repository.PostRepositoryImpl
import com.example.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePostRepository(api: PostApi, dao: PostDao): PostRepository {
        return PostRepositoryImpl(api, dao)
    }

    /*@Provides
    @Singleton
    fun provideGetAllPostsUseCase(
        postRepository: PostRepository
    ): GetAllPostsUseCase {
        return GetAllPostsUseCase(postRepository)
    }*/
}