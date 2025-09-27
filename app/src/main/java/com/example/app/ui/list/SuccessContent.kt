package com.example.app.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.model.Post

@Composable
fun SuccessContent(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    onPostClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
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