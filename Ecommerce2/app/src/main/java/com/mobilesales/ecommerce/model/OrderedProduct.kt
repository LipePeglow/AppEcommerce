package com.mobilesales.ecommerce.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import java.io.Serializable
import java.util.*

@Entity(tableName = "ordered_products", primaryKeys = ["orderedProductId","orderId"])
data class OrderedProduct (
    val orderedProductId: String = UUID.randomUUID().toString(),
    var orderId: String,
    @Embedded var product: Product,
    var size: String = "",
    var color : String = "",
    var quantity: Int = 0) : Serializable {

        @Ignore constructor(): this (UUID.randomUUID().toString(), "", Product(), "", "", 0)
    }
