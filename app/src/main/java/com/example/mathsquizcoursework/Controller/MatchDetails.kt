package com.example.devproject.Project.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.devproject.Project.Model.Booking
import com.example.devproject.Project.Model.Database
import com.example.devproject.R
import com.google.android.material.navigation.NavigationView
import java.util.*

class MatchDetails : AppCompatActivity() {

    var homeTeam : String?? = null
    var homeTeamImage : ByteArray?? = null
    var awayTeam : String?? = null
    var awayTeamImage : ByteArray?? = null
    var stadiumName : String?? = null
    var dateAndTime : String?? = null
    var p: Int?? = 0

    var name : String?? = null
    var d : String?? = null

    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        val drawerLayout : DrawerLayout = findViewById(R.id.navBar4)
        val nav : NavigationView = findViewById(R.id.navView4)

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

        homeTeam = intent.getStringExtra("homeTeam")
        homeTeamImage = intent.getByteArrayExtra("homeTeamImage")
        awayTeam = intent.getStringExtra("awayTeam")
        awayTeamImage = intent.getByteArrayExtra("awayTeamImage")
        stadiumName = intent.getStringExtra("stadiumName")
        dateAndTime = intent.getStringExtra("dateAndTime")
//        p = intent.getIntExtra("p")


        val book = findViewById<Button>(R.id.bookingBtn)
        val homeImage = findViewById<ImageView>(R.id.hTeamImg)
        val awayImage = findViewById<ImageView>(R.id.aTeamImg)
        val home = findViewById<TextView>(R.id.homeTV)
        val away = findViewById<TextView>(R.id.awayTV)
        val stadium = findViewById<TextView>(R.id.stdText)
        val dat = findViewById<TextView>(R.id.datText)
        val bookingBlock = findViewById<Spinner>(R.id.blockSpinner)
        val bookingRow = findViewById<Spinner>(R.id.rowSpinner)
        val bookingSeat = findViewById<Spinner>(R.id.seatSpinner)


        val name = findViewById<EditText>(R.id.editTextName)

        home.text = homeTeam
        away.text = awayTeam
        stadium.text = stadiumName
        dat.text = dateAndTime

        val myDataBase = Database(this)

        myDataBase.getAllSeats().map { it.seatNo }
        myDataBase.getAllSeats().map { it.SeatRow }
        myDataBase.getAllSeats().map { it.Block}


        val arrayList: ArrayList<Int> = ArrayList()
        for(num in 0 until myDataBase.getAllSeats().filter { it.SeatRow == "A" }.map { it.seatNo }.size){
            arrayList.add(myDataBase.getAllSeats().filter { it.SeatRow == "A" }.map { it.seatNo }.distinct()[num])
        }

        val seatNumberA: ArrayAdapter<Int> =
            ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, arrayList)
        seatNumberA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val arrayListB: ArrayList<Int> = ArrayList()
        for(num in 0 until myDataBase.getAllSeats().filter { it.SeatRow == "B" }.map { it.seatNo }.size){
            arrayListB.add(myDataBase.getAllSeats().filter { it.SeatRow == "B" }.map { it.seatNo }.distinct()[num])
        }

        val seatNumberB: ArrayAdapter<Int> =
            ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, arrayListB)
        seatNumberB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bookingSeat.setAdapter(seatNumberB)

        val arrayListC: ArrayList<Int> = ArrayList()
        for(num in 0 until myDataBase.getAllSeats().filter { it.SeatRow == "C" }.map { it.seatNo }.size) {
            arrayListC.add(myDataBase.getAllSeats().filter { it.SeatRow == "C" }.map { it.seatNo }.distinct()[num])
        }

        val seatNumberC: ArrayAdapter<Int> =
            ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, arrayListC)
        seatNumberC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bookingSeat.setAdapter(seatNumberC)

        val arrayListD: ArrayList<Int> = ArrayList()
        for(num in 0 until myDataBase.getAllSeats().filter { it.SeatRow == "D" }.map { it.seatNo }.size){
            arrayListD.add(myDataBase.getAllSeats().filter { it.SeatRow == "D" }.map { it.seatNo }.distinct()[num])
        }

        val seatNumberD: ArrayAdapter<Int> =
            ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, arrayListD)
        seatNumberD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bookingSeat.setAdapter(seatNumberD)

        val arrayListE: ArrayList<Int> = ArrayList()
        for(num in 0 until myDataBase.getAllSeats().filter { it.SeatRow == "E" }.map { it.seatNo }.size){
            arrayListE.add(myDataBase.getAllSeats().filter { it.SeatRow == "E" }.map { it.seatNo }.distinct()[num])
        }

        val seatNumberE: ArrayAdapter<Int> =
            ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, arrayListE)
        seatNumberE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bookingSeat.setAdapter(seatNumberE)

        val arrayListF: ArrayList<Int> = ArrayList()
        for(num in 0 until myDataBase.getAllSeats().filter { it.SeatRow == "F" }.map { it.seatNo }.size){
            arrayListF.add(myDataBase.getAllSeats().filter { it.SeatRow == "F" }.map { it.seatNo }.distinct()[num])
        }

        val seatNumberF: ArrayAdapter<Int> =
            ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, arrayListF)
        seatNumberF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bookingSeat.setAdapter(seatNumberF)

        val arrayList3: ArrayList<String> = ArrayList()
        for(num in 0..1){
            arrayList3.add(myDataBase.getAllBlocks().map { it.Block }[num].toString())
        }

        val blockk: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList3)
        blockk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bookingBlock.setAdapter(blockk)

        val arrayList4: ArrayList<String> = ArrayList()
        arrayList4.add(myDataBase.getAllSeats().filter { it.Block == "BlockA" }.map { it.SeatRow }.distinct()[0].toString())
        arrayList4.add(myDataBase.getAllSeats().filter { it.Block == "BlockA" }.map { it.SeatRow }.distinct()[1].toString())
        arrayList4.add(myDataBase.getAllSeats().filter { it.Block == "BlockA" }.map { it.SeatRow }.distinct()[2].toString())

        val sRow: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList4)
        sRow.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val arrayList5: ArrayList<String> = ArrayList()
        arrayList5.add(myDataBase.getAllSeats().filter { it.Block == "BlockB" }.map { it.SeatRow }.distinct()[0].toString())
        arrayList5.add(myDataBase.getAllSeats().filter { it.Block == "BlockB" }.map { it.SeatRow }.distinct()[1].toString())
        arrayList5.add(myDataBase.getAllSeats().filter { it.Block == "BlockB" }.map { it.SeatRow }.distinct()[2].toString())

        val sRow2: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList5)
        sRow2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        bookingBlock.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(bookingBlock.selectedItem == "A"){
                    bookingRow.setAdapter(sRow)
                }else {
                    bookingRow.setAdapter(sRow2)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        bookingRow.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(bookingRow.selectedItem == "A"){
                    bookingSeat.setAdapter(seatNumberA)
                }else if (bookingRow.selectedItem == "B") {
                    bookingSeat.setAdapter(seatNumberB)
                }else if (bookingRow.selectedItem == "C") {
                    bookingSeat.setAdapter(seatNumberC)
                }else if (bookingRow.selectedItem == "D") {
                    bookingSeat.setAdapter(seatNumberD)
                }else if (bookingRow.selectedItem == "E") {
                    bookingSeat.setAdapter(seatNumberE)
                }else if (bookingRow.selectedItem == "F") {
                    bookingSeat.setAdapter(seatNumberF)
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        val price = myDataBase.getAllMatches().filter { it.DateAndTime == dat.text.toString()}.map { it.Price }[0]

        book.setOnClickListener {
            val intent = Intent(this, BookingDetails::class.java)

            myDataBase.addBooking(Booking(-1, name.text.toString(), bookingBlock.selectedItem.toString(), dat.text.toString(), bookingSeat.selectedItem.toString().toInt(), bookingRow.selectedItem.toString(),price))

            myDataBase.deleteSeat(bookingSeat.selectedItem.toString())

            intent.putExtra("name", name.text.toString())
            intent.putExtra("d", dat.text.toString())
            intent.putExtra("homeTeam", home.text.toString())
            intent.putExtra("awayTeam", away.text.toString())
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