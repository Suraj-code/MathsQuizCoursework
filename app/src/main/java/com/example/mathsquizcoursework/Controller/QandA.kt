package com.example.mathsquizcoursework.Controller

import android.content.Context
import com.example.mathsquizcoursework.Model.Answers
import com.example.mathsquizcoursework.Model.MathsQuiz
import com.example.mathsquizcoursework.Model.Questions
import kotlin.random.Random

class QandA (context: Context) {


    private val questionsList: ArrayList<Questions>
    private val answersList: ArrayList<Answers>
    private val dbHelper: MathsQuiz = MathsQuiz(context)
    private val questions: ArrayList<Questions> = arrayListOf()

    init {

        questionsList = dbHelper.getAllQuestions()
        answersList = dbHelper.getAllAnswers()

    }

    fun get_questions(): ArrayList<Questions> {
        return questionsList
    }

    fun get_answers(): ArrayList<Answers>{
        return answersList
    }

    //Randomly gets 14 questions from the pool of 42 questions
    fun RQuestions(): ArrayList<Questions> {

        questions.add(questionsList.distinct().filter { it.TopicId == 1 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 1 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 2 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 2 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 3 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 3 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 4 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 4 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 5 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 5 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 6 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 6 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 7 }.distinct().get(Random.nextInt(6))).toString()
        questions.add(questionsList.distinct().filter { it.TopicId == 7 }.distinct().get(Random.nextInt(6))).toString()

        return questions

    }


}