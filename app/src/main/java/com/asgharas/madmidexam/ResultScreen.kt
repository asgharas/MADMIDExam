package com.asgharas.madmidexam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    correctAnswers: Int, totalQuestions: Int, onRestartQuiz: () -> Unit
) {

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Final Results") })
    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Subject:")
                    Text(text = "Geography", color = Color.LightGray)
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Correct:")
                    Text(text = "$correctAnswers", color = Color.LightGray)
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Incorrect:")
                    Text(text = "${totalQuestions - correctAnswers}", color = Color.LightGray)
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Earned:")
                    Text(text = "${correctAnswers * 10}", color = Color.LightGray)
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Overall Points:")
                    Text(text = "${totalQuestions * 10}", color = Color.LightGray)
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Date:")
                    Text(text = "${Date()}", color = Color.LightGray)
                }
            }

            Button(onClick = { onRestartQuiz() }) {
                Text(text = "START AGAIN")
            }
        }
    }
}