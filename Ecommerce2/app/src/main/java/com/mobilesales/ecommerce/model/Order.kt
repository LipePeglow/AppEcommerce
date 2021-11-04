package com.mobilesales.ecommerce.model

import java.io.Serializable

data class Order (

    val id: String,
    val time: Long,
    val status: Status,
    val method: Method,
    val user: User,
    val products: MutableList<OrderedProduct> = emptyList<OrderedProduct>().toMutableList(),
    val price: Double = products.sumOf{ it.quantity * it.product.price }): Serializable {

        enum class Status (val message: String){
            PENDENT("Pendente"),
            PAID("Pago"),
            PROCESSED("Processado")
        }

    enum class Method (val message: String){
        CREDT_CARD("Cartao de Credito"),
        BOLETO("Boleto"),

    }
    }