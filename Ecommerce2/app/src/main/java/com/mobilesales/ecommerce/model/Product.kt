package com.mobilesales.ecommerce.model

import java.io.Serializable


data class Product (

    val id: String,
    val title: String,
    val category: ProductCategory,
    val description: String,
    val prices: Double,
    val colors: List<ProductColor>,
    val sizes: List<ProductSize>,
    val images: List<ProductImage>)  : Serializable