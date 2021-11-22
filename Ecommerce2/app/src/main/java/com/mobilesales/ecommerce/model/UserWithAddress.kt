package com.mobilesales.ecommerce.model

import android.location.Address
import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation

data class UserWithAddress (
    @Embedded var user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val addresses: MutableList<UserAddress> = mutableListOf()){

    @Ignore constructor(): this(User(), mutableListOf())
}