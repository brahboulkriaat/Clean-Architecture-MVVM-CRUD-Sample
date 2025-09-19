package com.example.domain.usecase

import com.example.domain.repository.PostRepository

class GetPostByIdUseCase /*@Inject constructor*/(private val postRepository: PostRepository) {
    operator fun invoke(id: Int) = postRepository.getPostById(id)
}