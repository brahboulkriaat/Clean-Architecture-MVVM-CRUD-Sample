package com.example.sample.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.data.repository.PostRepository
import com.example.sample.domain.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    init {
        refreshPosts()
    }

    val posts: StateFlow<List<Post>> = repository.posts
        //.map { it }
        .stateIn(
            viewModelScope,
            /*SharingStarted.Companion.WhileSubscribed(5_000),*/ SharingStarted.Lazily,
            emptyList()
        )

    fun refreshPosts() {
        viewModelScope.launch {
            try {
                repository.getAndSavePosts()
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error fetching posts", e)
            }
        }
    }
}