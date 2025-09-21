package com.example.data.repository

import com.example.data.local.PostDao
import com.example.data.mapper.toDomain
import com.example.data.remote.PostApi
import com.example.domain.model.Post
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.example.core.util.Result
import com.example.data.mapper.toEntity
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class PostRepositoryImpl @Inject constructor(
    private val api: PostApi,
    private val dao: PostDao
) : PostRepository {
    // val posts: Flow<List<Post>> = dao.getAllPosts().map { posts -> posts.map { it.toDomain() } } //TODO("mapper")

    /*suspend fun getAndSavePosts() {
        try {
            val posts = api.getPosts()
            dao.insertAllPosts(posts.map { it.toEntity() }) //TODO("mapper")
        } catch (e: Exception) {
            Log.e("PostRepository", "Error fetching posts", e)
        }
    }*/

    override fun getAllPosts(): Flow<Result<List<Post>>> = flow {
        emit(Result.Loading)
        try {
            val cached = dao.getAllPosts().first()
            emit(Result.Success(cached.map { it.toDomain() }))

            val remotePosts = api.getPosts()
            dao.insertAllPosts(remotePosts.map { it.toEntity() })

            // Re-emit updated list from database
            emitAll(dao.getAllPosts().map { Result.Success(it.map { entity -> entity.toDomain() }) })

        } catch (e: Exception) {
            emit(Result.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    // dao.getAllPosts().map { posts -> posts.map { it.toDomain() } } //TODO("mapper")

    override fun getPostById(id: Int): Flow<Result<Post>> = flow {
        emit(Result.Loading)
        try {
            dao.getPostById(id).collect { entity ->
                if (entity != null) {
                    emit(Result.Success(entity.toDomain()))
                } else {
                    emit(Result.Error("Post not found"))
                }
            }
        } catch (e: Exception) {
            emit(Result.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    // dao.getPostById(id).map { it?.toDomain() } //TODO("mapper") -> posts.map { it.toDomain() } } //TODO("mapper")
}