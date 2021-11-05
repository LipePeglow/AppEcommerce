package com.mobilesales.ecommerce.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "Users")
data class User (

    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "e-mail")var email: String,
    var nome: String,
    var surName: String,
    var password: String,
    var image: String) : Serializable