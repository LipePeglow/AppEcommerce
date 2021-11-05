package com.mobilesales.ecommerce.model

import androidx.room.Embedded
import androidx.room.Relation

data class OrderWithOrderedProducts(

    @Relation(
        parentColumn = "id",
        entityColumn = "orderId"
    )
    @Embedded val order: Order,
    val product: MutableList<OrderedProduct> = emptyList<OrderedProduct>().toMutableList())