package com.mobilesales.ecommerce.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "order")


    data class Order (
@PrimaryKey val id: String = UUID.randomUUID().toString(),
var time: Long,
var status: Status,
var method: Method,
var userId: String,
var price: Double = 0.0) : Serializable {

    enum class Status(val message: String) {
        PENDENT("Pendente"),
        PAID("Pago"),
        PROCESSED("Enviado"),
        CART("Carrinho abandonado")
    }

    enum class Method(val message: String) {
        CREDIT_CARD("Cartão de Crédito"),
        BOLETO("Boleto"),
        NOME("Nenhum")
    }

}

