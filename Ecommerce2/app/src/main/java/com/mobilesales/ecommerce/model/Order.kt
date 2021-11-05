package com.mobilesales.ecommerce.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "orders")
data class Order (

    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var time: Long,
    var status: Status,
    var method: Method,
    var userId: String,
    val price: Double ): Serializable {

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