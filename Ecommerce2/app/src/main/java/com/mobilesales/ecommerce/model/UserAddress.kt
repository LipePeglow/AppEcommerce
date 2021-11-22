package com.mobilesales.ecommerce.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "user_adresses")
data class UserAddress(

    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var userId: String,
    var addressLine1: String,
    var addressLine2: String,
    var number: String,
    var state: String,
    var city: String,
    var zipCode: String,
    var country: String = "Brasil") : Serializable {
    @Ignore constructor(): this("","","","","","","","","Brasil")
    }