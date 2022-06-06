package com.example.mathsquizcoursework.Controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mathsquizcoursework.Model.MathsQuiz
import com.example.mathsquizcoursework.Model.Student
import com.example.mathsquizcoursework.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*


class ProgressChart : AppCompatActivity() {

    var userName: String?? = null
    var Score: Int?? = null
    var button: Int?? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_chart)

        userName = intent.getStringExtra("UserName")
        Score = intent.getIntExtra("Score", 0)
        button = intent.getIntExtra("button",0)

        val mButton = findViewById<Button>(R.id.mainPageButton)

        mButton.setOnClickListener {

            val intent = Intent(this, MainActivity  ::class.java)
            startActivity(intent)
            finish()

        }

        val barchart = findViewById<BarChart>(R.id.barchart)
        val nameTV = findViewById<TextView>(R.id.NametextView)

        nameTV.text = userName +"'s " + "Progress Results"

        val xaxis = ArrayList<String>()
        xaxis.add("Test1")
        xaxis.add("Test2")
        xaxis.add("Test3")
        xaxis.add("Test4")
        xaxis.add("Test5")

       val lineentry = ArrayList<BarEntry>()
        var count: Int = 0

        val myDataBase = MathsQuiz(this)
        lineentry.add(BarEntry(myDataBase.getAllGrades(Student(userName.toString(),0,0,0,0,0))[0].toFloat(), 0))
        lineentry.add(BarEntry(myDataBase.getAllGrades(Student(userName.toString(),0,0,0,0,0))[1].toFloat(), 1))
        lineentry.add(BarEntry(myDataBase.getAllGrades(Student(userName.toString(),0,0,0,0,0))[2].toFloat(), 2))
        lineentry.add(BarEntry(myDataBase.getAllGrades(Student(userName.toString(),0,0,0,0,0))[3].toFloat(), 3))
        lineentry.add(BarEntry(myDataBase.getAllGrades(Student(userName.toString(),0,0,0,0,0))[4].toFloat(), 4))
            //count++



        val bardataSet = BarDataSet(lineentry,"Label")
        bardataSet.color = resources.getColor(R.color.teal_200)

        val data = BarData(xaxis,bardataSet)
        barchart.data = data
        barchart.setBackgroundColor(resources.getColor(R.color.white))
        barchart.animateXY(3000,3000)

    }



}
