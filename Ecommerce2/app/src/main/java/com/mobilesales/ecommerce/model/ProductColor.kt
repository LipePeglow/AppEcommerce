package com.mobilesales.ecommerce.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "products_colors")
data class ProductColor (

    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var productId: String,
    var name: String,
    var code: String) : Serializable