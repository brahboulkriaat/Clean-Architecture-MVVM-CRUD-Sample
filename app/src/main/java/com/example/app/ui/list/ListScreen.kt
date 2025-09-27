package com.example.app.ui.list

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.ui.MainViewModel
import com.example.app.ui.common.ErrorContent
import com.example.app.ui.common.LoadingContent
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
                val posts = (result as Result.Success<List<Post>>).data
                SuccessContent(
                    modifier = Modifier
                        .padding(it)
                    /*.padding(16.dp)*/,
                    posts = posts,
                    onPostClick = onPostClick
                )
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