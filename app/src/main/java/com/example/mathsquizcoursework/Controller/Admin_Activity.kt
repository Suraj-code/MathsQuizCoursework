package com.example.mathsquizcoursework.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mathsquizcoursework.R

class admin_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val loginBtn = findViewById<Button>(R.id.button)


        loginBtn.setOnClickListener {

                val intent = Intent(this, addQuestions::class.java)
                startActivity(intent)
                finish()
            }

        }

}