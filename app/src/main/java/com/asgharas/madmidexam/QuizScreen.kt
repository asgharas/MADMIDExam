package com.asgharas.madmidexam

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun QuizScreen(onQuizFinished: (Int) -> Unit) {
    val allQuestions = allQuestionTexts.indices.map { index ->
        Question(
            text = stringResource(allQuestionTexts[index]),
            options = stringArrayResource(allOptions[index]).toList(),
            answerIndex = integerResource(allAnswers[index])
        )
    }
    val context = LocalContext.current

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    val currentQuestion by remember { derivedStateOf { allQuestions[currentQuestionIndex] } }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    val userAnswers = remember { mutableStateListOf<Int?>() }


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = currentQuestion.text, style = MaterialTheme.typography.displaySmall)

        Column(Modifier.align(Alignment.Start)) {
            currentQuestion.options.forEachIndexed { index, option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = selectedOption == index,
                        onClick = { selectedOption = index })
                    Text(text = option)
                }

            }
        }

        Text(
            text = "Current Question: ${currentQuestionIndex + 1}",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = {
            if (currentQuestionIndex < 4) {
                userAnswers.add(selectedOption)
                selectedOption = null
                currentQuestionIndex++
            } else {
                selectedOption = null
                currentQuestionIndex = 0
                var correctAnsweredCount = 0
                userAnswers.forEachIndexed { index, userAnswer ->
                    if (userAnswer == allQuestions[index].answerIndex) {
                        correctAnsweredCount++
                    }
                }
                Toast.makeText(context, "You answered $correctAnsweredCount questions correctly", Toast.LENGTH_SHORT).show()
                userAnswers.clear()
                onQuizFinished(correctAnsweredCount)
            }
        }) {
            Text(text = "Next")
        }
    }
}


val allQuestionTexts = listOf(
    R.string.question_1,
    R.string.question_2,
    R.string.question_3,
    R.string.question_4,
    R.string.question_5
)
val allOptions = listOf(
    R.array.options_question_1,
    R.array.options_question_2,
    R.array.options_question_3,
    R.array.options_question_4,
    R.array.options_question_5
)
val allAnswers = listOf(
    R.integer.answer_index_1,
    R.integer.answer_index_2,
    R.integer.answer_index_3,
    R.integer.answer_index_4,
    R.integer.answer_index_5
)

data class Question(
    val text: String, val options: List<String>, val answerIndex: Int
)

