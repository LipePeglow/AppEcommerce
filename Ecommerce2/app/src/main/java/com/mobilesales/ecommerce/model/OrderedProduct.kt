package com.mobilesales.ecommerce.model

import java.io.Serializable

data class OrderedProduct (

    var id: String,
    var product: Product,
    var quantity: Int) : Serializable
