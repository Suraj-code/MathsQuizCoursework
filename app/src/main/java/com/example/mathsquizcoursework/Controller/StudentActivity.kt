package com.example.mathsquizcoursework.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mathsquizcoursework.R

class StudentActivity : AppCompatActivity() {

    var userName : String?? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        val buttonStart = findViewById<View>(R.id.btn_start)
        val EditNameText = findViewById<TextView>(R.id.nameText)


        buttonStart.setOnClickListener {

            if(EditNameText.text.toString().isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, QuizzQuestions::class.java)
                intent.putExtra("UserName", EditNameText.text.toString())
                startActivity(intent)
                finish()
            }

        }

    }
}
