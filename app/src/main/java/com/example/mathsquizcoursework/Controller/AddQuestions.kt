package com.example.mathsquizcoursework.Controller

import android.R.string
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mathsquizcoursework.Model.MathsQuiz
import com.example.mathsquizcoursework.R


class addQuestions : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_questions)

        val backBtn = findViewById<Button>(R.id.bckButton)

        backBtn.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        val topicDropdown = findViewById<Spinner>(R.id.TopicS)
        val questionDropDown = findViewById<Spinner>(R.id.QuestionS)
        val rightAnsDropDown = findViewById<Spinner>(R.id.RightAnsS)
        val wrongAnsDropDown = findViewById<Spinner>(R.id.WrongAnsS)
        val wrongAnsDropDown2 = findViewById<Spinner>(R.id.WrongAns2S)

        val myDataBase = MathsQuiz(this)

        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add(myDataBase.getAllTopics().map { it.TopicName }[0].toString())
        arrayList.add(myDataBase.getAllTopics().map { it.TopicName }[1].toString())
        arrayList.add(myDataBase.getAllTopics().map { it.TopicName }[2].toString())
        arrayList.add(myDataBase.getAllTopics().map { it.TopicName }[3].toString())
        arrayList.add(myDataBase.getAllTopics().map { it.TopicName }[4].toString())
        arrayList.add(myDataBase.getAllTopics().map { it.TopicName }[5].toString())
        arrayList.add(myDataBase.getAllTopics().map { it.TopicName }[6].toString())


        val topic = findViewById<TextView>(R.id.topicTV)
        val question = findViewById<TextView>(R.id.QuestionTV)
        val rightAns = findViewById<TextView>(R.id.RightAnsTV)
        val wrongAns = findViewById<TextView>(R.id.WrongAnsTV)
        val wrongAns2= findViewById<TextView>(R.id.WrongAns1)

        topic.text = "Topic: "
        question.text = "Question: "
        rightAns.text = "Right Answer: "
        wrongAns.text = "Wrong Answer: "
        wrongAns2.text = "Wrong Answer: "

        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        topicDropdown.setAdapter(arrayAdapter)

    }
}