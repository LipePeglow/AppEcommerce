package com.mobilesales.ecommerce

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mobilesales.ecommerce.fragment.CartFragment
import com.mobilesales.ecommerce.fragment.ProductFragment
import com.mobilesales.ecommerce.model.ProductCategory
import kotlinx.android.synthetic.main.menu_toolbar_layout.*

class CartActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.cart_title)


        val fragment = CartFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_cart, fragment)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
