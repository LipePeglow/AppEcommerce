package com.mobilesales.ecommerce.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithProducts (

    @Embedded val category: ProductCategory,
    @Relation(
    parentColumn = "id",
        entityColumn = "categoryId"
)
    val products: List<Product>)