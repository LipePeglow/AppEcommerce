package com.mobilesales.ecommerce.model

import android.location.Address
import androidx.room.Embedded
import androidx.room.Relation

data class UserWithAddress (
    @Embedded val user : User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val adress : List<UserAddress> = emptyList(),
    )