package com.mobilesales.ecommerce.database

import androidx.room.*
import com.mobilesales.ecommerce.model.CategoryWithProducts
import com.mobilesales.ecommerce.model.ProductCategory

@Dao
interface ProductCategoryDao {

    @Query("SELECT * FROM product_categories")
    fun loadAll (): List<ProductCategory>
    @Query("SELECT * FROM product_categories WHERE featured = 1")
    fun loadAllFeatured(): List<ProductCategory>
    @Transaction
    @Query("SELECT * FROM product_categories WHERE id = :categoryId")
    fun loadCategoryWithProductsById (categoryId: String): List<CategoryWithProducts>

    @Insert
    fun insert(category: ProductCategory)
    @Update
    fun update(category: ProductCategory)


}