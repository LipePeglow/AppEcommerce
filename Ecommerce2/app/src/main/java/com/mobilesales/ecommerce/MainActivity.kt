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
import com.mobilesales.ecommerce.adapter.ProductAdapter
import com.mobilesales.ecommerce.adapter.ProductCategoryAdapter
import com.mobilesales.ecommerce.fragment.ProductCategoryFragment
import com.mobilesales.ecommerce.model.Product
import com.mobilesales.ecommerce.model.ProductCategory
import com.mobilesales.ecommerce.model.ProductColor
import com.mobilesales.ecommerce.model.ProductSize

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

        recyclerCaterogy = findViewById(R.id.recyclerview_product_category_main)

        val arrayCategory = arrayListOf<ProductCategory>(
            ProductCategory("1", "Camisetas",fillRvProduct()),
            ProductCategory("2", "Calças",fillRvProduct()),
            ProductCategory("3", "Meias"),
            ProductCategory("4", "Tênis"),
            ProductCategory("5", "Casacos"))

        val adapterCategory = ProductCategoryAdapter(arrayCategory, this)

        recyclerCaterogy.adapter = adapterCategory
        recyclerCaterogy.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerProduct = findViewById(R.id.recyclerview_product)


        val adapterProduct = ProductAdapter(fillRvProduct(), this)
        recyclerProduct.adapter = adapterProduct
        recyclerProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun fillRvProduct() : List<Product>{

        val product1: Product = Product(
            "1","Camiseta 89",
            ProductCategory("id", "Camisetas"),
            "Camisseta Leve.",
            19.90,
            arrayListOf(
                ProductColor("1","Branco","#ffffff"),
                ProductColor("2","Preta","#000000")),
            arrayListOf(
                ProductSize("1", "P",),
                ProductSize("1", "M")),
            emptyList())


        val product2: Product = Product(
            "1","Calça Jeans",
            ProductCategory("id", "Calças"),
            "Calça com proteção para chuva.",
            39.90,
            arrayListOf(
                ProductColor("1","Branco","#ffffff"),
                ProductColor("2","Preta","#000000")),
            arrayListOf(
                ProductSize("1", "G",),
                ProductSize("1", "GG")),
            emptyList())

        return arrayListOf(product1, product2)
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
            R.id.nav_orders -> Toast.makeText(this, "Compras", Toast.LENGTH_LONG).show()
            R.id.nav_cart -> Toast.makeText(this, "Carrinho", Toast.LENGTH_LONG).show()
            R.id.nav_logout -> Toast.makeText(this, "Sair", Toast.LENGTH_LONG).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun itemSelector(category: ProductCategory) {

        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
    }
}