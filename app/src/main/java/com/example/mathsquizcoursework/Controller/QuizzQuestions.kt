package com.example.mathsquizcoursework.Controller

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.mathsquizcoursework.Model.Answers
import com.example.mathsquizcoursework.Model.MathsQuiz
import com.example.mathsquizcoursework.Model.Questions
import com.example.mathsquizcoursework.Model.Student
import com.example.mathsquizcoursework.R
import kotlin.random.Random

class QuizzQuestions : AppCompatActivity(), View.OnClickListener {

    lateinit var AllQandA : QandA
    lateinit var quizQuestions : ArrayList<Questions>
    lateinit var quizAnswers : ArrayList<Answers>

    lateinit var RandomQuestions : ArrayList<Questions>

    var currentScore : Int = 0
    var qNum : Int = 0
    var userName: String?? = null
    var Score: String?? = "CurrentScore"
    var selectedOptionsPosition: Int = 1
    var ans: String = ""
    var optNum : Int = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_questions)

        userName = intent.getStringExtra("UserName")

        AllQandA = QandA(this)
        quizQuestions = AllQandA.get_questions() //get all questions
        quizAnswers = AllQandA.get_answers() //get all answers
        RandomQuestions = AllQandA.RQuestions() //gets 14 random questions

        var Question_Text = findViewById<TextView>(R.id.question_text)

        var NextQuestion = findViewById<Button>(R.id.NextButton)

        var opt1 = findViewById<TextView>(R.id.option1).setOnClickListener(this)
        var opt2 = findViewById<TextView>(R.id.option2).setOnClickListener(this)
        var opt3 = findViewById<TextView>(R.id.option3).setOnClickListener(this)

        val option1 = findViewById<TextView>(R.id.option1)
        val option2 = findViewById<TextView>(R.id.option2)
        val option3 = findViewById<TextView>(R.id.option3)

        //sets the first page after user enters their name
        defaultOptionsVIew()
        getSelectedQuestionsNew()
        NextQuestion.isEnabled = false

        NextQuestion.setOnClickListener {

            if(NextQuestion.text != "Submit"){

                NextQuestion.isEnabled = false
                defaultOptionsVIew()
                getSelectedQuestionsNew()

            }else{

                //checks if the user is already in the database and adds their test scores accordingly
                val myDataBase = MathsQuiz(this)
                if(myDataBase.checkUserExists(Student(userName.toString(), currentScore, 0,0,0,0))){

                    if(myDataBase.getAllGrades(Student(userName.toString(), 0, 0,0,0,0))[1].equals(0)){
                        myDataBase.updateTest2Marks(Student(userName.toString(),0,currentScore,0,0,0))
                    }else if(myDataBase.getAllGrades(Student(userName.toString(), 0, 0,0,0,0))[2].equals(0)){
                        myDataBase.updateTest3Marks(Student(userName.toString(),0,0,currentScore,0,0))
                    }else if(myDataBase.getAllGrades(Student(userName.toString(), 0, 0,0,0,0))[3].equals(0)){
                        myDataBase.updateTest4Marks(Student(userName.toString(),0,0,0,currentScore,0))
                    }else if(myDataBase.getAllGrades(Student(userName.toString(), 0, 0,0,0,0))[4].equals(0)){
                        myDataBase.updateTest5Marks(Student(userName.toString(),0,0,0,0,currentScore))
                    }

                    //if user is not in the database then add user
                }else {
                    myDataBase.addStudent(Student(userName.toString(), currentScore, 0, 0, 0, 0))
                }

                println(myDataBase.getAllStudents())

                val intent = Intent(this, ResultsActivity::class.java)
                intent.putExtra("UserName", userName)
                intent.putExtra("Score", currentScore)
                startActivity(intent)
                finish()
            }


        }


    }

    //this function does the following: if the user has not selected an answer they cannot progress to the next question
    fun ValidationForAnswers(t: TextView, optionNum: Int){

        val optionList = ArrayList<TextView>()

        optionList.add(0,findViewById<TextView>(R.id.option1))
        optionList.add(1,findViewById<TextView>(R.id.option2))
        optionList.add(2,findViewById<TextView>(R.id.option3))

        for(o in optionList){

                findViewById<Button>(R.id.NextButton).isEnabled = true

            }

        }


    //This function sets the questions
    fun getSelectedQuestionsNew(){

        findViewById<TextView>(R.id.question_text).text = (qNum + 1).toString() + ") " + RandomQuestions.map { it.Question }[qNum]

        if((qNum + 1) == RandomQuestions.size){
            findViewById<Button>(R.id.NextButton).text = "Submit"
        }else {
            findViewById<Button>(R.id.NextButton).text = "Next"
        }

        val optionList = ArrayList<TextView>()

        optionList.add(0,findViewById<TextView>(R.id.option1))
        optionList.add(1,findViewById<TextView>(R.id.option2))
        optionList.add(2,findViewById<TextView>(R.id.option3))

        val i: Int = quizAnswers.filter { it.QuestionId == RandomQuestions[qNum].QuestionId }.filter { it.CorrectAnswer == 1 }.map { it.Answers }.toString().length


        for(o in optionList){


            if(optNum < 3) {
                optionList[0].text = quizAnswers.filter { it.QuestionId == RandomQuestions[qNum].QuestionId }.filter { it.CorrectAnswer == 1 }.map { it.Answers }.toString().substring(1, (i - 1))

                optionList[optNum].text = quizAnswers.distinct().filter { it.QuestionId == RandomQuestions[qNum].QuestionId }
                    .map { it.Answers }.get(Random.nextInt(1,3))

                optionList[optNum + 1].text = quizAnswers.distinct().filter { it.QuestionId == RandomQuestions[qNum].QuestionId }
                    .map { it.Answers }.get(Random.nextInt(3,5))

                //optNum++

                ans = quizAnswers.filter { it.QuestionId == RandomQuestions[qNum].QuestionId }.filter { it.CorrectAnswer == 1 }.map { it.Answers }.toString()

            }else {
                optNum = 0
            }

        }

        qNum++

    }

    //This function checks if the selected textview contains the correct answer.
    fun checkCorrectAnswer(t: TextView, optionNum: Int){

        selectedOptionsPosition = optionNum

        if("[" + t.text + "]" == ans){
            currentScore++
        }else{
            println("Incorrect")

        }

    }

    //This function sets the colour and layout of the textview when it is not selected
    fun defaultOptionsVIew(){

        val optionList = ArrayList<TextView>()

        optionList.add(0,findViewById<TextView>(R.id.option1))
        optionList.add(1,findViewById<TextView>(R.id.option2))
        optionList.add(2,findViewById<TextView>(R.id.option3))

        for(o in optionList){

            o.setTextColor(Color.parseColor("#7A8089"))
            o.typeface = Typeface.DEFAULT
            o.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)


        }

    }

    //This function changes the colour and layout of the textview when selected to let the user know which textview is selected
    fun selectedOptionsView(t: TextView, optionNum: Int){

        defaultOptionsVIew()
        selectedOptionsPosition = optionNum

        t.setTextColor(Color.BLACK)
        t.setTypeface(t.typeface, Typeface.BOLD)
        t.background = ContextCompat.getDrawable(this, R.drawable.selected_options)


    }

    //This is for textviews when they are clicked
    override fun onClick(v: View?) {
        when(v?.id){

            R.id.option1 -> {
                selectedOptionsView(findViewById(R.id.option1), 1)
                checkCorrectAnswer(findViewById(R.id.option1), 1)
                ValidationForAnswers(findViewById(R.id.option1), 1)

            }
            R.id.option2 -> {
                selectedOptionsView(findViewById(R.id.option2), 2)
                checkCorrectAnswer(findViewById(R.id.option2), 2)
                ValidationForAnswers(findViewById(R.id.option2), 2)

            }
            R.id.option3 -> {
                selectedOptionsView(findViewById(R.id.option3), 3)
                checkCorrectAnswer(findViewById(R.id.option3), 3)
                ValidationForAnswers(findViewById(R.id.option3), 3)

            }


        }
    }

}









