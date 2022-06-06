package com.example.mathsquizcoursework.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.mathsquizcoursework.R

class ResultsActivity : AppCompatActivity() {

    var userName : String?? = null
    var Score : Int?? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        userName = intent.getStringExtra("UserName")
        Score = intent.getIntExtra("Score",0)

        val button = findViewById<Button>(R.id.ProgressButton)

        button.setOnClickListener {

            val intent = Intent(this, ProgressChart::class.java)
            intent.putExtra("UserName", userName)
            intent.putExtra("Score", Score)
            intent.putExtra("button",button.id)
            startActivity(intent)
            finish()

        }

        //displays the user's name and score
        findViewById<TextView>(R.id.user_name).text = userName
        findViewById<TextView>(R.id.Score_tv).text = "Your score is ${Score} out of 14"



    }


}


