package com.mobilesales.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.adapter.ProductCategoryAdapter
import com.mobilesales.ecommerce.fragment.ProductFragment
import com.mobilesales.ecommerce.model.ProductCategory


class ProductActivity : AppCompatActivity() {


    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.product_title)

        val category = intent.getSerializableExtra("CATEGORY") as ProductCategory

        val args = Bundle()
        args.putSerializable("CATEGORY", category)
        val fragment = ProductFragment()
        fragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_product, fragment)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
 }

