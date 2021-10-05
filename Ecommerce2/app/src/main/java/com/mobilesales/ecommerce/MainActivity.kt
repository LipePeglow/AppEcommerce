package com.mobilesales.ecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.mobilesales.ecommerce.adapter.ProductCategoryAdapter
import com.mobilesales.ecommerce.model.ProductCategory

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar:Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navgationView: NavigationView
    lateinit var textTitle: TextView
    lateinit var textLogin: TextView
    lateinit var recyclerCaterogy: RecyclerView



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

        val productItem: LinearLayout = findViewById(R.id.ll_product_item)
        productItem.setOnClickListener{
            val intent: Intent = Intent(this, ProductDetailActivity::class.java)
            startActivity(intent)
        }

        textLogin = navgationView.getHeaderView(0).findViewById(R.id.header_profile_name)
        textLogin.setOnClickListener {

           val intent = Intent (this, UserLoginActivity::class.java)
            startActivity(intent)
        }

        recyclerCaterogy = findViewById(R.id.recyclerview_product_category_main)

        val arrayCategory = arrayListOf<ProductCategory>(ProductCategory("1", "Camisetas"), ProductCategory("2", "Calças"),
            ProductCategory("3", "Meias"), ProductCategory("4", "Tênis"), ProductCategory("5", "Casacos"))

        val adapterCategory = ProductCategoryAdapter(arrayCategory, this)

        recyclerCaterogy.adapter = adapterCategory
        recyclerCaterogy.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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

            R.id.nav_category -> Toast.makeText(this, "Categoria", Toast.LENGTH_LONG).show()
            R.id.nav_orders -> Toast.makeText(this, "Compras", Toast.LENGTH_LONG).show()
            R.id.nav_cart -> Toast.makeText(this, "Carrinho", Toast.LENGTH_LONG).show()
            R.id.nav_logout -> Toast.makeText(this, "Sair", Toast.LENGTH_LONG).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}