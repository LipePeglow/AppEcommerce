package com.mobilesales.ecommerce.database


import androidx.lifecycle.LiveData
import androidx.room.*
import com.mobilesales.ecommerce.model.User
import com.mobilesales.ecommerce.model.UserAddress
import com.mobilesales.ecommerce.model.UserWithAddress
import kotlinx.coroutines.selects.select

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    fun login(email : String, password : String) : LiveData<User>
    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    fun loadUserById(userId : String) : LiveData<UserWithAddress>

    @Insert
    fun insert (user: User)
    @Update
    fun update(user: User)
    @Insert
    fun insert (userAddress: UserAddress)
    @Update
    fun update (userAddress: UserAddress)
}