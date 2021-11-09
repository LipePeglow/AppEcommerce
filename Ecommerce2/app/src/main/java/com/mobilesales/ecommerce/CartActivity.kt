package com.mobilesales.ecommerce

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.mobilesales.ecommerce.fragment.CartFragment
import com.mobilesales.ecommerce.fragment.ProductFragment
import com.mobilesales.ecommerce.model.ProductCategory
import com.mobilesales.ecommerce.viewModel.CartViewModel
import kotlinx.android.synthetic.main.menu_toolbar_layout.*

class CartActivity : AppCompatActivity(), CartFragment.callBack {

    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    lateinit var cartTotal : TextView
    private val cartViewModel by viewModels<CartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.cart_title)

        cartTotal = findViewById(R.id.tv_total)

        cartViewModel.cartPrice.observe(this, Observer{
            cartTotal.text = "R$ ${it}"
        })

        val fragment = CartFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_cart, fragment)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun updateCart() {
        cartViewModel.cartPrice.value = CartViewModel.order.price
    }
}
