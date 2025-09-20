package com.example.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Post
import com.example.domain.usecase.GetAllPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getAllPostsUseCase: GetAllPostsUseCase
) : ViewModel() {

    init {
        //refreshPosts()
    }

    val posts: StateFlow<List<Post>> = getAllPostsUseCase()
        //.map { it }
        .stateIn(
            viewModelScope,
            /*SharingStarted.Companion.WhileSubscribed(5_000),*/ SharingStarted.Lazily,
            emptyList()
        )

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