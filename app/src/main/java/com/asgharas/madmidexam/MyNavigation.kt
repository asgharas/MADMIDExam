package com.asgharas.madmidexam

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MyNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash-screen") {
        composable("splash-screen") {
            MySplashScreen { navController.navigate("quiz-screen") }
        }
        composable("quiz-screen") {
            QuizScreen { correctAnswersCount ->
                navController.navigate("result-screen/$correctAnswersCount")
            }
        }
        composable(
            "result-screen/{correct-answers-count}",
            arguments = listOf(
                navArgument("correct-answers-count") { type = NavType.IntType }
            )
        ) {
            val correctAnswersCount = it.arguments?.getInt("correct-answers-count") ?: 0
            ResultScreen(correctAnswers = correctAnswersCount, totalQuestions = 5) {
                navController.navigate("quiz-screen")
            }
        }

    }
}