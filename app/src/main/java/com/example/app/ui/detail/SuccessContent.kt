package com.example.app.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.model.Post

@Composable
fun SuccessContent(
    modifier: Modifier = Modifier,
    post: Post
) {
    Column(
        modifier = modifier
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