package com.mobilesales.ecommerce.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "product_images")
data  class ProductImage (

    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var path: String ) : Serializable
 