package com.mobilesales.ecommerce.model

import java.io.Serializable

data class User (

    val id: String,
    val email: String,
    val nome: String,
    val surName: String,
    val password: String,
    val image: String,
    val adress: List<UserAdress> = emptyList()) : Serializable