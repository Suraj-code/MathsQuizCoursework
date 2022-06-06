package com.example.devproject.Project.Controller

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.devproject.Project.Model.Database
import com.example.devproject.R
import com.google.android.material.navigation.NavigationView


class MatchesPage : AppCompatActivity() {

    var index = 0
    var homeTeam : String?? = null
    var homeTeamImage : ByteArray?? = null
    var awayTeam : String?? = null
    var awayTeamImage : ByteArray?? = null
    var stadiumName : String?? = null
    var dateAndTime : String?? = null
    var p: Int?? = 0
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches_page)

        val drawerLayout : DrawerLayout = findViewById(R.id.navBar)
        val nav : NavigationView = findViewById(R.id.navView)
        val user = findViewById<TextView>(R.id.userName)
        val email = findViewById<TextView>(R.id.email)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.navHome -> {
                    val intent = Intent(this, MatchesPage::class.java)
                    startActivity(intent)
                    finish()
                }

                R.id.navRegister -> {
                    val intent = Intent(this, RegisterPage::class.java)
                    startActivity(intent)
                    finish()
                }

                R.id.navAdmin -> {
                    val intent = Intent(this, AdminActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                R.id.navLogin -> {
                    val intent = Intent(this, LoginPage::class.java)
                    startActivity(intent)
                    finish()
                }

                R.id.navLogout -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

            true


        }

        val button = findViewById<Button>(R.id.btn_ticket1)
        val nextButton = findViewById<Button>(R.id.buttonNext)
        val prevButton = findViewById<Button>(R.id.buttonPrev)

//        val myImage: ByteArray = anwserList[imageIndex].anwserImage // get image
//        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)
//
//        findViewById<ImageView>(R.id.imageView).setImageBitmap(bmp)

        val myDataBase = Database(this)

        println(myDataBase.getAllMatches())

        val hImage: ByteArray = myDataBase.getAllMatches().map { it.HomeTeamImg }[0]
        val bmp: Bitmap = BitmapFactory.decodeByteArray(hImage, 0, hImage.size)

        val aImage: ByteArray = myDataBase.getAllMatches().map { it.AwayTeamImg }[0]
        val bmp2: Bitmap = BitmapFactory.decodeByteArray(aImage, 0, aImage.size)

        val hTeam = findViewById<TextView>(R.id.homeTeamName)
        val hTeamL = findViewById<ImageView>(R.id.homeTeamLogo).setImageBitmap(bmp)
        val aTeam = findViewById<TextView>(R.id.awayTeamName)
        val aTeamL = findViewById<ImageView>(R.id.awayTeamLogo).setImageBitmap(bmp2)
        val stadium = findViewById<TextView>(R.id.stdName)
        val dat = findViewById<TextView>(R.id.dateAndTimeText)


        hTeam.text = myDataBase.getAllMatches().map { it.HomeTeam }[0]

        aTeam.text = myDataBase.getAllMatches().map { it.AwayTeam }[0]

        stadium.text = myDataBase.getAllMatches().map { it.Stadium }[0]
        dat.text = myDataBase.getAllMatches().map { it.DateAndTime }[0]
//        p = myDataBase.getAllMatches().filter { it.MatchId == 1 }.map { it.Price }[0]

        var num = 1

        nextButton.setOnClickListener {

            if(index+1 != myDataBase.getAllMatches().size) {

                index++
                val hImage: ByteArray = myDataBase.getAllMatches().map { it.HomeTeamImg }[index]
                val bmp: Bitmap = BitmapFactory.decodeByteArray(hImage, 0, hImage.size)

                val aImage: ByteArray = myDataBase.getAllMatches().map { it.AwayTeamImg }[index]
                val bmp2: Bitmap = BitmapFactory.decodeByteArray(aImage, 0, aImage.size)

                val hTeamL = findViewById<ImageView>(R.id.homeTeamLogo).setImageBitmap(bmp)
                val aTeamL = findViewById<ImageView>(R.id.awayTeamLogo).setImageBitmap(bmp2)

                hTeam.text = myDataBase.getAllMatches().map { it.HomeTeam }[index]
                aTeam.text = myDataBase.getAllMatches().map { it.AwayTeam }[index]
                stadium.text = myDataBase.getAllMatches().map { it.Stadium }[index]
                dat.text = myDataBase.getAllMatches().map { it.DateAndTime }[index]
//                p = myDataBase.getAllMatches().filter { it.MatchId == num }.map { it.Price }[index]

            } else Toast.makeText(this,"Sorry, No More Matches", Toast.LENGTH_LONG).show()

        }

        prevButton.setOnClickListener {

            if(index-1 >= 0) {

                index--
                val hImage: ByteArray = myDataBase.getAllMatches().map { it.HomeTeamImg }[index]
                val bmp: Bitmap = BitmapFactory.decodeByteArray(hImage, 0, hImage.size)

                val aImage: ByteArray = myDataBase.getAllMatches().map { it.AwayTeamImg }[index]
                val bmp2: Bitmap = BitmapFactory.decodeByteArray(aImage, 0, aImage.size)

                val hTeamL = findViewById<ImageView>(R.id.homeTeamLogo).setImageBitmap(bmp)
                val aTeamL = findViewById<ImageView>(R.id.awayTeamLogo).setImageBitmap(bmp2)

                hTeam.text = myDataBase.getAllMatches().map { it.HomeTeam }[index]
                aTeam.text = myDataBase.getAllMatches().map { it.AwayTeam }[index]
                stadium.text = myDataBase.getAllMatches().map { it.Stadium }[index]
                dat.text = myDataBase.getAllMatches().map { it.DateAndTime }[index]
//                p = myDataBase.getAllMatches().filter { it.MatchId == num }.map { it.Price }[index]

            } else Toast.makeText(this,"Sorry, No More Matches", Toast.LENGTH_LONG).show()

        }

        button.setOnClickListener {

            val intent = Intent(this, MatchDetails::class.java)
            intent.putExtra("homeTeam", hTeam.text.toString())
//            intent.putExtra("homeTeamImage", hImage)
            intent.putExtra("awayTeam", aTeam.text.toString())
//            intent.putExtra("awayTeamImage", aImage)
            intent.putExtra("stadiumName", stadium.text.toString())
            intent.putExtra("dateAndTime", dat.text.toString())
            intent.putExtra("p", p.toString())
            startActivity(intent)
            finish()

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}


//
//fun previousButton(view: View) {
//
//    if(index-1 >= 0) {
//        index--
//        findViewById<TextView>(R.id.brandNameData).text =  myStore.get_BrandName(phoneList.get(index).brandID) // return brand
//        findViewById<TextView>(R.id.phoneNameData).text =  phoneList.get(index).Name // return mobile name
//        findViewById<TextView>(R.id.phoneConditionData).text =  Condition.values()[phoneList.get(index).condition].toString()
//        findViewById<TextView>(R.id.phonePriceData).text =  phoneList.get(index).price.toString()
//    }
//    else Toast.makeText(this,"Sorry, No More Phones", Toast.LENGTH_LONG).show()
//
//}