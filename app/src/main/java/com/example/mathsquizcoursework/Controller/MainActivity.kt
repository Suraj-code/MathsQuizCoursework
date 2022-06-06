package com.example.mathsquizcoursework.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mathsquizcoursework.R


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val student = findViewById<Button>(R.id.btn_student)
        val admin = findViewById<Button>(R.id.btn_admin)

        //takes user to the student tab
        student.setOnClickListener {

            val intent = Intent(this, StudentActivity::class.java)
            startActivity(intent)
            finish()

        }

        //takes user to the admin login page
        admin.setOnClickListener {

            val intent = Intent(this, admin_Activity::class.java)
            startActivity(intent)
            finish()

        }

    }


}