package com.mobilesales.ecommerce


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.mobilesales.ecommerce.adapter.ProductAdapter
import com.mobilesales.ecommerce.adapter.ProductCategoryAdapter
import com.mobilesales.ecommerce.fragment.ProductCategoryFragment
import com.mobilesales.ecommerce.model.*

import com.mobilesales.ecommerce.viewModel.ProductViewModel
import com.mobilesales.ecommerce.viewModel.UserViewModel

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
ProductCategoryFragment.Callback{

    lateinit var toolbar:Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navgationView: NavigationView
    lateinit var textTitle: TextView
    lateinit var textLogin: TextView
    lateinit var recyclerCaterogy: RecyclerView
    lateinit var recyclerProduct: RecyclerView
    lateinit var imageProfile : ImageView
    private val productViewModel by  viewModels<ProductViewModel>()
    private val userViewmodel by  viewModels<UserViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.app_name)

        drawerLayout = findViewById(R.id.nav_drawer_layout)


        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.toggle_open, R.string.toggle_close)
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        navgationView = findViewById(R.id.nav_view)
        navgationView.setNavigationItemSelectedListener(this)

        textLogin = navgationView.getHeaderView(0).findViewById(R.id.header_profile_name)
        textLogin.setOnClickListener {

           val intent = Intent (this, UserLoginActivity::class.java)
            startActivity(intent)
        }

        imageProfile = navgationView.getHeaderView(0).findViewById(R.id.header_profile_image)

        recyclerCaterogy = findViewById(R.id.recyclerview_product_category_main)

        val adapterCategory = ProductCategoryAdapter( this)

        productViewModel.featuredCategories.observe(this, Observer {
            adapterCategory.list = it
            adapterCategory.notifyDataSetChanged()
        })


        recyclerCaterogy.adapter = adapterCategory
        recyclerCaterogy.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerProduct = findViewById(R.id.recyclerview_product)


        val adapterProduct = ProductAdapter(this)

        productViewModel.featuredProducts.observe(this, Observer{
            adapterProduct.list = it
            adapterProduct.notifyDataSetChanged()
        })

        recyclerProduct.adapter = adapterProduct
        recyclerProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.nav_home -> {
               val intent = Intent (this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_account -> {
                val intent = Intent (this, UserProfileActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_category ->{
                val intent = Intent (this, ProductCategoryActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_orders -> {
                val intent = Intent(this, OrderActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_cart ->{
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_logout -> {
                userViewmodel.logout()
                finish()
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun itemSelector(category: ProductCategory) {

        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        userViewmodel.isLogged().observe(this,Observer{ user ->
            user?.let {
                textLogin.text = "${it.user.name} ${it.user.surname}"
                userViewmodel.loadProfile(it.user.id, imageProfile)
            }
        })
    }
}