package com.mobilesales.ecommerce.model

import androidx.room.Embedded
import androidx.room.Relation

data class OrderWithOrderedProducts(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val product: MutableList<OrderedProduct> = emptyList<OrderedProduct>().toMutableList())