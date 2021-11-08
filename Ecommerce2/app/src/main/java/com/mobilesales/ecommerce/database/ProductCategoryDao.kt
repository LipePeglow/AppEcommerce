package com.mobilesales.ecommerce.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mobilesales.ecommerce.model.CategoryWithProducts
import com.mobilesales.ecommerce.model.ProductCategory

@Dao
interface ProductCategoryDao {

    @Query("SELECT * FROM product_categories")
    fun loadAll (): LiveData<List<ProductCategory>>
    @Query("SELECT * FROM product_categories WHERE featured = 1")
    fun loadAllFeatured(): LiveData<List<ProductCategory>>
    @Transaction
    @Query("SELECT * FROM product_categories WHERE id = :categoryId")
    fun loadCategoryWithProductsById (categoryId: String):  LiveData<CategoryWithProducts>

    @Insert
    fun insert(category: ProductCategory)
    @Update
    fun update(category: ProductCategory)


}