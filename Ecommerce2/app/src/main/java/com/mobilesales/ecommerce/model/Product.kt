package com.mobilesales.ecommerce.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(tableName = "products")
data class Product (

    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var title: String,
    var categoryId: String,
    var description: String,
    var price: Double,
    val thumbnail: String,
    val featured: Boolean = false): Serializable