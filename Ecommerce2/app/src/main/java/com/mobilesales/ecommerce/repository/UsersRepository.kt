package com.mobilesales.ecommerce.repository

import android.app.Application
import com.mobilesales.ecommerce.database.AppDataBase
import com.mobilesales.ecommerce.model.User
import com.mobilesales.ecommerce.model.UserAddress

class UsersRepository (application: Application) {

    private val userDao = AppDataBase.getDataBase(application).userDao()

    fun login(email : String, password : String) = userDao.login(email, password)

    fun loadWithAdresses(userId : String) = userDao.loadUserById(userId)

    fun insert(user : User) = userDao.insert(user)

    fun insert(userAddress : UserAddress) = userDao.insert(userAddress)

    fun updateUser(user : User) = userDao.update(user)

    fun updateUser(userAddress : UserAddress) = userDao.update(userAddress)
}