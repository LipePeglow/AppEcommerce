package com.mobilesales.ecommerce.database

import androidx.room.*
import com.mobilesales.ecommerce.model.Product
import com.mobilesales.ecommerce.model.ProductVariants

@Dao
interface ProductDao {

    @Query("SELECT * FROM products WHERE categoryId = :categoryId")
    fun loadAllByCategory(categoryId : String): List<Product>
    @Query("SELECT * FROM products WHERE featured = 1")
    fun loadAllFeatured(): List<Product>
    @Transaction
    @Query("SELECT * FROM products WHERE id = :productId")
    fun loadProductWithVariants(productId : String): ProductVariants

    @Insert
    fun insert(product : Product)
    @Update
    fun update(product : Product)
}