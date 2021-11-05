package com.mobilesales.ecommerce.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "products_sizes")
data class ProductSize (

    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var productId : String,
    var size: String) : Serializable