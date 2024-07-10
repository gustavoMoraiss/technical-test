package com.training.interviewtechnicaltest.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigationGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = repositoriesScreenRoute
    ) {
        repositoriesScreen(navHostController = navHostController)
        pullRequestsScreen(navHostController = navHostController)
    }
}