package com.mobilesales.ecommerce

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar



class UserProfileActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = "Meu Perfil"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}