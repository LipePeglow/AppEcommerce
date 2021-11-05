package com.mobilesales.ecommerce.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "Adress")
data class UserAddress(

    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var userID: String,
    var adressLine1: String,
    var adressLine2: String,
    var number: String,
    var state: String,
    var city: String,
    var zipeCode: String,
    var country: String) : Serializable