package com.training.interviewtechnicaltest

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        content = {
            NavigationGraph(navController = navController)
        }
    )
}

@Composable
private fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "Repositories"
    ) {
        composable(route = "Repositories") {}
        composable(route = "PullRequests") {}
    }
}
