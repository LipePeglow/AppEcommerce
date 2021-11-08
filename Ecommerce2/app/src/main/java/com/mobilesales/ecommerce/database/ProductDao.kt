package com.mobilesales.ecommerce.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mobilesales.ecommerce.model.Product
import com.mobilesales.ecommerce.model.ProductVariants

@Dao
interface ProductDao {

    @Query("SELECT * FROM products WHERE categoryId = :categoryId")
    fun loadAllByCategory(categoryId : String): LiveData<List<Product>>
    @Query("SELECT * FROM products WHERE featured = 1")
    fun loadAllFeatured(): LiveData<List<Product>>
    @Transaction
    @Query("SELECT * FROM products WHERE id = :productId")
    fun loadProductWithVariants(productId : String): LiveData<ProductVariants>

    @Insert
    fun insert(product : Product)
    @Update
    fun update(product : Product)
}