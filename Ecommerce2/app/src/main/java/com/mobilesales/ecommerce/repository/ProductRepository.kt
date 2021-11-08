package com.mobilesales.ecommerce.repository

import android.app.Application
import com.mobilesales.ecommerce.database.AppDataBase
import com.mobilesales.ecommerce.model.Product
import com.mobilesales.ecommerce.model.ProductVariants

class ProductsRepository (application: Application) {

    private val productDao = AppDataBase.getDataBase(application).productDao()

    private val productCategoryDao = AppDataBase.getDataBase(application).productCategoryDao()

    val allCategories = productCategoryDao.loadAll()

    val featuredCategories = productCategoryDao.loadAllFeatured()

    val featuredProducts = productDao.loadAllFeatured()

    fun loadProductsByCategory(categoryId: String) = productDao.loadAllByCategory(categoryId)

    fun loadProductById(productId: String) = productDao.loadProductWithVariants(productId)
}