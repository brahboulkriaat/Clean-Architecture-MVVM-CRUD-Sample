package com.example.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sample.ui.theme.SampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleTheme {
                MainNavHost()
            }
        }
    }
}

@Composable
private fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        addListGraph(navController)
        addDetailsGraph(navController)
    }
}

private fun NavGraphBuilder.addListGraph(navController: NavController) {
    composable("list") {
        ListScreen { postId ->
            navController.navigate("detail/$postId")
        }
    }
}

private fun NavGraphBuilder.addDetailsGraph(navController: NavHostController) {
    composable("detail/{postId}") { backStackEntry ->
        val postId = backStackEntry.arguments?.getString("postId")?.toIntOrNull()
        if (postId != null) {
            DetailsScreen(postId)
        }
    }
}

@Composable
private fun ListScreen(
    viewModel: PostViewModel = hiltViewModel(),
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

@Composable
private fun DetailsScreen(
    postId: Int,
    viewModel: PostViewModel = hiltViewModel()
) {
    val post = viewModel.posts.collectAsState().value.find { it.id == postId }

    if (post != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.body)
        }
    } else {
        Text("Post not found")
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