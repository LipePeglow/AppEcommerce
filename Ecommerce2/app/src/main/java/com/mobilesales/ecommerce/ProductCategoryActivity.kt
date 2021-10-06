package com.mobilesales.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.adapter.ProductCategoryAdapter
import com.mobilesales.ecommerce.model.ProductCategory


class ProductCategoryActivity : AppCompatActivity() {

    lateinit var recyclerCaterogy: RecyclerView
    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_category)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.categorias)


        recyclerCaterogy = findViewById(R.id.recyclerview_product_category)

        val arrayCategory = arrayListOf<ProductCategory>(
            ProductCategory("1", "Camisetas"),
            ProductCategory("2", "Calças"),
            ProductCategory("3", "Meias"),
            ProductCategory("4", "Tênis"),
            ProductCategory("5", "Casacos")
        )

        val adapterCategory = ProductCategoryAdapter(arrayCategory, this)

        recyclerCaterogy.adapter = adapterCategory
        recyclerCaterogy.layoutManager = GridLayoutManager(this, 2)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
 }

