package com.example.devproject.Project.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.devproject.Project.Model.Database
import com.example.devproject.R

class deleteMatches : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_matches)

        val mId = findViewById<EditText>(R.id.matchIdText)
        val delete = findViewById<Button>(R.id.deleteBtn)

        val myDataBase = Database(this)

        delete.setOnClickListener {
            if(myDataBase.deleteMatch(mId.text.toString())){
                Toast.makeText(this, "Match deleted!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}