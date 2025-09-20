package com.example.app.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.ui.MainViewModel

@Composable
fun DetailsScreen(
    id: Int,
    viewModel: MainViewModel = hiltViewModel()
) {
    val post by viewModel.getPost(id).collectAsState(null)

    if (post != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = post!!.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post!!.body,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        Box(modifier = Modifier.padding(16.dp)) {
            Text("Post not found")
        }
    }
}