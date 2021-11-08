package com.mobilesales.ecommerce.repository

import android.app.Application
import com.mobilesales.ecommerce.database.AppDataBase
import com.mobilesales.ecommerce.model.Product
import com.mobilesales.ecommerce.model.ProductVariants

class ProductRepository(application: Application) {

    private val productDao = AppDataBase.getDataBase(application).productDao()

    private val productCategoryDao = AppDataBase.getDataBase(application).productCategoryDao()

    val allCategories = productCategoryDao.loadAll()

    val featuredCategories = productCategoryDao.loadAllFeatured()

    val featuredProducts = productDao.loadAllFeatured()

    fun loadProductsByCategory(categoryId: String) : List<Product> = productDao.loadAllByCategory(categoryId)

    fun loadProductById(productId: String) : ProductVariants = productDao.loadProductWithVariants(productId)
}