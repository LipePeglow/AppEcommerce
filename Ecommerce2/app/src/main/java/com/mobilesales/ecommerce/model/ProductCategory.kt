package com.mobilesales.ecommerce.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "product_category")
data class ProductCategory (

    @PrimaryKey val id : String = UUID.randomUUID().toString(),
    var title: String,
    val featured : Boolean = false) : Serializable

