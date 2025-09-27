package com.example.app.ui.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.ui.MainViewModel
import com.example.app.ui.common.ErrorContent
import com.example.app.ui.common.LoadingContent
import com.example.domain.model.Post
import com.example.domain.utill.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    id: Int,
    viewModel: MainViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Post Details") })
        }
    ) {
        val result by viewModel.getPost(id).collectAsState(Result.Loading)

        when (result) {
            is Result.Loading -> {
                LoadingContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                )
            }

            is Result.Error -> {
                val message = (result as Result.Error).message
                ErrorContent(
                    modifier = Modifier.fillMaxSize(),
                    message = message
                )
            }

            is Result.Success -> {
                val post = (result as Result.Success<Post>).data
                SuccessContent(
                    modifier = Modifier
                        .padding(it)
                    .padding(16.dp),
                    post = post
                )
            }
        }
    }
}