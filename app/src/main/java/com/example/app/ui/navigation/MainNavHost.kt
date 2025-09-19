package com.example.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.detail.DetailsScreen
import com.example.app.ui.list.ListScreen

@Composable
fun MainNavHost() {
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