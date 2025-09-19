package com.example.data.remote

import com.example.data.remote.dto.PostDto
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    suspend fun getPosts(): List<PostDto>
}