package com.training.interviewtechnicaltest

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.training.interviewtechnicaltest.core.navigation.MainNavigationGraph

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        content = {
            MainNavigationGraph(navHostController = navController)
        }
    )
}
