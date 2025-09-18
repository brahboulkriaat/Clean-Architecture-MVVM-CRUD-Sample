package com.example.sample.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sample.ui.MainViewModel
import com.example.sample.ui.theme.SampleTheme

@Composable
fun ListScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onPostClick: (Int) -> Unit
) {
    val posts by viewModel.posts.collectAsState()

    LazyColumn {
        items(posts) { post ->
            ListItem(
                headlineContent = { Text(post.title) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onPostClick(post.id) }
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreview() {
    SampleTheme {
        ListScreen {
        }
    }
}