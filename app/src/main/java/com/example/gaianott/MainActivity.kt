package com.example.gaianott

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    lateinit var  btnSearch :TextView
    lateinit var  btnHome :TextView
    lateinit var  btnTvshow :TextView
    lateinit var  btnMovie :TextView
    lateinit var  btnSports :TextView
    lateinit var  btnSetting :TextView
    lateinit var  btnLanguage :TextView
    lateinit var  btnGenre :TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch = findViewById(R.id.btn_search)
        btnHome = findViewById(R.id.btn_home)
        btnTvshow = findViewById(R.id.btn_tv)
        btnMovie = findViewById(R.id.btn_movies)

        btnSports = findViewById(R.id.btn_sports)
        btnSetting = findViewById(R.id.btn_settings)
        btnLanguage = findViewById(R.id.btn_language)
        btnGenre = findViewById(R.id.btn_genre)

        changeFragment(HomeFragment())

    }
    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()


    }
}