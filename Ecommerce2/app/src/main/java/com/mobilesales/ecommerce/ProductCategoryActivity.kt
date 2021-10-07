package com.mobilesales.ecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.adapter.ProductCategoryAdapter
import com.mobilesales.ecommerce.fragment.ProductCategoryFragment
import com.mobilesales.ecommerce.fragment.ProductFragment
import com.mobilesales.ecommerce.model.ProductCategory


class ProductCategoryActivity : AppCompatActivity(), ProductCategoryFragment.Callback {


    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    var isTablet : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_category)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.categorias)

        isTablet = findViewById<View>(R.id.fragment_product ) != null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun itemSelector(category: ProductCategory) {

        if (isTablet){

            val args = Bundle()
            args.putSerializable("CATEGORY", category)
            val fragment = ProductFragment()
            fragment.arguments = args
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_product, fragment)
                .commit()

        }else{

            val intent = Intent(this, ProductActivity::class.java)
            intent.putExtra("CATEGORY", category)
            startActivity(intent)
        }
    }
}

