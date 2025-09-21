package com.example.domain.usecase

import com.example.domain.model.Post
import com.example.domain.repository.PostRepository
import com.example.domain.utill.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    operator fun invoke(): Flow<Result<List<Post>>> = postRepository.getAllPosts()
}