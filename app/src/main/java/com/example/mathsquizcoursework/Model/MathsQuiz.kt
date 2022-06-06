package com.example.mathsquizcoursework.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/* Database Config*/
private val DataBaseName = "MathsQuizApp.db"
private val ver : Int = 1


class MathsQuiz(context: Context) : SQLiteOpenHelper(context, DataBaseName,null , ver) {

    /* Topic Table */
    public val TopicTableName = "Topic"
    public val TopicColumn_ID = "Topic Id"
    public val Column_TopicName = "Topic Name"

    /*************************/

    /* Questions Table */
    public val QuestionTableName = "Question"
    public val QuestionColumn_ID = "Question Id"
    public val Questions = "Question"
    public val QuestionForeignKey = "Topic Id"

    /*************************/

    /*Answers Table*/
    public val AnswersTableName = "Answer"
    public val AnswersColumnId = "Answer Id"
    public val AnswersColumn = "Answers"
    public val AnswerForeignKey = "Question Id"
    public val CorrectAnswer = "Correct Answer"

    /*************************/

    /*Students Table*/
    public val StudentTableName = "StudentTable"
    public val StudentTableColumnId = "StudentId"
    public val StudentName = "StudentName"
    public val StudentTest1 = "Test1"
    public val StudentTest2 = "Test2"
    public val StudentTest3 = "Test3"
    public val StudentTest4 = "Test4"
    public val StudentTest5 = "Test5"


    /*************************/

    // This is called the first time a database is accessed
    // Create a new database
    override fun onCreate(db: SQLiteDatabase?) {

        try {
            var sqlCreateStatement: String =
                "CREATE TABLE " + TopicTableName + " ( " + TopicColumn_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_TopicName + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + QuestionTableName + " ( " + QuestionColumn_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Questions + " TEXT NOT NULL, " + QuestionForeignKey + " INTEGER, " +
                    " FOREIGN KEY ("+QuestionForeignKey+") REFERENCES "+TopicTableName+" ("+TopicColumn_ID+"));";

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + AnswersTableName + " ( " + AnswersColumnId +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + AnswersColumn + " TEXT NOT NULL, " + CorrectAnswer + " INTEGER, " + AnswerForeignKey+ " INTEGER, " +
                    " FOREIGN KEY ("+AnswerForeignKey+") REFERENCES "+QuestionTableName+" ("+QuestionColumn_ID+"));";

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement =
                "CREATE TABLE $StudentTableName ( $StudentName TEXT PRIMARY KEY NOT NULL, $StudentTest1 INTEGER, $$StudentTest2 INTEGER, $StudentTest3 INTEGER, $StudentTest4 INTEGER, $StudentTest5 INTEGER )"

            db?.execSQL(sqlCreateStatement)
        }
        catch(e : SQLException) { }

        }



    // This is called if the database ver. is changed
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun deleteStudent(student: Student): Boolean {

        val db: SQLiteDatabase = this.writableDatabase

        val result = db.delete(StudentTableName, "$StudentName = ${student.sName}", null) == 1

        db.close()
        return result

    }

    //Adds a student to the database
    fun addStudent(student: Student): Boolean {

            // writableDatabase for insert actions
            val db: SQLiteDatabase = this.writableDatabase
            val cv: ContentValues = ContentValues()

            cv.put(StudentName, student.sName)
            cv.put(StudentTest1, student.Test1)
            cv.put(StudentTest2, student.Test2)
            cv.put(StudentTest3, student.Test3)
            cv.put(StudentTest4, student.Test4)
            cv.put(StudentTest5, student.Test5)

            val success = db.insert(StudentTableName, null, cv)
            db.close()
            return success != -1L


    }

    //updates the database with test2 marks
    fun updateTest2Marks(student: Student): Boolean {

        // writableDatabase for insert actions
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(StudentTest2, student.Test2)

        val result = db.update(StudentTableName, cv, "$StudentName=?", arrayOf(student.sName)) == 1
        db.close()
        return result
    }

    //updates the database with test3 marks
    fun updateTest3Marks(student: Student): Boolean {

        // writableDatabase for insert actions
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(StudentTest3, student.Test3)

        val result = db.update(StudentTableName, cv, "$StudentName=?", arrayOf(student.sName)) == 1
        db.close()
        return result
    }

    //updates the database with test4 marks
    fun updateTest4Marks(student: Student): Boolean {

        // writableDatabase for insert actions
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(StudentTest4, student.Test4)

        val result = db.update(StudentTableName, cv, "$StudentName=?", arrayOf(student.sName)) == 1
        db.close()
        return result
    }

    //updates the database with test5 marks
    fun updateTest5Marks(student: Student): Boolean {

        // writableDatabase for insert actions
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        //cv.put(StudentName, student.sName)

        cv.put(StudentTest5, student.Test5)

        val result = db.update(StudentTableName, cv, "$StudentName=?", arrayOf(student.sName)) == 1
        db.close()
        return result
    }

    //Gets all the grades for a particular user and adds it to an ArrayList
    fun getAllGrades(student: Student): ArrayList<Int> {

        val studentList = ArrayList<Int>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $StudentTableName WHERE $StudentName = ?"

        val studentName = student.sName

        val cursor: Cursor = db.rawQuery(sqlStatement, arrayOf(student.sName))

        if (cursor.moveToFirst())
            do {
                val Test1: Int = cursor.getInt(2)
                val Test2: Int = cursor.getInt(3)
                val Test3: Int = cursor.getInt(4)
                val Test4: Int = cursor.getInt(5)
                val Test5: Int = cursor.getInt(6)
                studentList.add(Test1)
                studentList.add(Test2)
                studentList.add(Test3)
                studentList.add(Test4)
                studentList.add(Test5)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return studentList
    }

    //Checks if the user exists on the database
    fun checkUserExists(student: Student): Boolean {

        val db: SQLiteDatabase = this.readableDatabase

        val studentName = student.sName

        val sqlStatement = "SELECT * FROM $StudentTableName WHERE $StudentName = ?"
        val param = arrayOf(studentName)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return true // error the student already exists
        }

        cursor.close()
        db.close()
        return false //Student not found

    }

    //gets all the students and adds them to an Arraylist
    fun getAllStudents(): ArrayList<Student> {

        val studentList = ArrayList<Student>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $StudentTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val StudentId: Int = cursor.getInt(0)
                val sName: String = cursor.getString(1)
                val Test1: Int = cursor.getInt(2)
                val Test2: Int = cursor.getInt(3)
                val Test3: Int = cursor.getInt(4)
                val Test4: Int = cursor.getInt(5)
                val Test5: Int = cursor.getInt(6)
                val s = Student( sName, Test1, Test2, Test3, Test4, Test5)
                studentList.add(s)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return studentList
    }

    fun getAllTopics(): ArrayList<Topic> {

        val topicList = ArrayList<Topic>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $TopicTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val topicId: Int = cursor.getInt(0)
                val topicName: String = cursor.getString(1)
                val t = Topic(topicId, topicName)
                topicList.add(t)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return topicList
    }

    //Gets all the questions from the database
    fun getAllQuestions(): ArrayList<Questions> {

        val questionList = ArrayList<Questions>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $QuestionTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val questionId: Int = cursor.getInt(0)
                val questions: String = cursor.getString(1)
                val topicId: Int = cursor.getInt(2)
                val q = Questions(questionId, questions, topicId )
                questionList.add(q)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return questionList
    }

    //Gets all the answers from the database
    fun getAllAnswers(): ArrayList<Answers> {

        val answersList = ArrayList<Answers>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $AnswersTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val answerId: Int = cursor.getInt(0)
                val answers: String = cursor.getString(1)
                val questionId: Int = cursor.getInt(2)
                val correctAnswer: Int = cursor.getInt(3)
                val a = Answers(answerId, answers, questionId, correctAnswer )
                answersList.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return answersList
    }

}