package com.example.app.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.ui.MainViewModel
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
                //TODO LoadingScreen()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Result.Error -> {
                //TODO ErrorScreen((result as Result.Error).message)
                val message = (result as Result.Error).message
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it), contentAlignment = Alignment.Center
                ) {
                    Text("Error: $message", color = MaterialTheme.colorScheme.error)
                }
            }

            is Result.Success -> {
                //TODO DetailsScreen(post = (result as Result.Success<Post>).data)
                val post = (result as Result.Success<Post>).data
                Column(
                    modifier = Modifier
                        .padding(it)
                        .padding(16.dp)
                ) {
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = post.body,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}