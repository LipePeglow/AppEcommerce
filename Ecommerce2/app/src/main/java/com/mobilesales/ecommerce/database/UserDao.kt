package com.mobilesales.ecommerce.database


import androidx.room.*
import com.mobilesales.ecommerce.model.User
import com.mobilesales.ecommerce.model.UserAddress
import com.mobilesales.ecommerce.model.UserWithAddress
import kotlinx.coroutines.selects.select

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :userId")
    fun loadUserById(userId : String) : User
    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    fun loadUserWithAddress(userId : String) : UserWithAddress

    @Insert
    fun insert (user: User)
    @Update
    fun update(user: User)
    @Insert
    fun insert (userAddress: UserAddress)
    @Update
    fun update (userAddress: UserAddress)
}