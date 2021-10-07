package com.mobilesales.ecommerce.model

import java.io.Serializable

data class Order (

    val id: String,
    val time: Long,
    val status: String,
    val method: String,
    val user: User,
    val products: MutableList<OrderedProduct> = emptyList<OrderedProduct>().toMutableList(),
    val price: Double = products.sumByDouble { it.quantity * it.product.price }): Serializable