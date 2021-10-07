package com.mobilesales.ecommerce.model

import java.io.Serializable

data class UserAdress(

    val id: String,
    val user: User,
    val adressLine1: String,
    val adressLine2: String,
    val number: String,
    val state: String,
    val city: String,
    val zipeCode: String,
    val country: String) : Serializable