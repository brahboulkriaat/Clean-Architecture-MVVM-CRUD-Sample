package com.example.app.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.ui.MainViewModel
import com.example.app.ui.common.LoadingScreen
import com.example.app.ui.theme.SampleTheme
import com.example.domain.model.Post
import com.example.domain.utill.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onPostClick: (Int) -> Unit
) {
    val result by viewModel.posts.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Posts List") })
        }) {
        when (result) {
            is Result.Loading -> {
                LoadingScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                )
            }

            is Result.Error -> {
                //TODO ErrorScreen((result as Result.Error).message)
                val message = (result as Result.Error).message
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: $message", color = MaterialTheme.colorScheme.error)
                }
            }

            is Result.Success -> {
                val posts = (result as Result.Success<List<Post>>).data
                //TODO ListScreen(posts = posts, onPostClick = onPostClick)
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .padding(16.dp)

                ) {
                    items(posts) { post ->
                        ListItem(
                            headlineContent = { Text(post.title) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onPostClick(post.id) })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreview() {
    SampleTheme {
        ListScreen {}
    }
}