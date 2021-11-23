package com.mobilesales.ecommerce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.mobilesales.ecommerce.fragment.CartFragment
import com.mobilesales.ecommerce.fragment.ProductFragment
import com.mobilesales.ecommerce.model.Order
import com.mobilesales.ecommerce.model.ProductCategory
import com.mobilesales.ecommerce.viewModel.CartViewModel
import com.mobilesales.ecommerce.viewModel.OrderViewModel
import com.mobilesales.ecommerce.viewModel.UserViewModel
import kotlinx.android.synthetic.main.menu_toolbar_layout.*

class CartActivity : AppCompatActivity(), CartFragment.callBack {

    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    lateinit var cartTotal : TextView
    lateinit var btnFinish : Button

    private val cartViewModel by viewModels<CartViewModel>()
    private val orderViewModel by viewModels<OrderViewModel>()
    private val userViewModel by viewModels<UserViewModel>()

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
        btnFinish = findViewById(R.id.btn_finish)

        btnFinish.setOnClickListener {
            userViewModel.isLogged().observe(this, Observer {
                if (it != null){
                    val fullOder = CartViewModel.getFullOrder()
                    fullOder.order.userId = it.user.id
                    orderViewModel.place(fullOder)
                    startActivity(Intent(this, OrderActivity::class.java))
                }else{
                    startActivity(Intent(this,UserLoginActivity::class.java))
                    finish()
                }
            })
        }

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
        cartViewModel.cartPrice.value = CartViewModel.getFullOrder().order.price
    }
}
