package com.example.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Post
import com.example.domain.usecase.GetAllPostsUseCase
import com.example.domain.usecase.GetPostByIdUseCase
import com.example.domain.utill.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    getAllPostsUseCase: GetAllPostsUseCase,
    private val getPostByIdUseCase: GetPostByIdUseCase
) : ViewModel() {

    init {
        //refreshPosts()
    }

    val posts: StateFlow<Result<List<Post>>> = getAllPostsUseCase()
        //.map { it }
        .stateIn(
            viewModelScope,
            /*SharingStarted.Companion.WhileSubscribed(5_000),*/ SharingStarted.Lazily,
            Result.Loading
        )

    fun getPost(id: Int): Flow<Result<Post>> = getPostByIdUseCase(id)

    /*fun refreshPosts() {
        viewModelScope.launch {
            try {
                repository.getAndSavePosts()
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error fetching posts", e)
            }
        }
    }*/
}