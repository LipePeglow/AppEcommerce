package com.mobilesales.ecommerce.model

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import java.io.Serializable

data class OrderWithOrderedProducts(
    @Embedded var order: Order,
    @Relation(
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val product: MutableList<OrderedProduct> = emptyList<OrderedProduct>().toMutableList()) : Serializable{

        @Ignore constructor() : this(Order(), mutableListOf())
    }