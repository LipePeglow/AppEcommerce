package com.mobilesales.ecommerce.repository

import android.app.Application
import com.mobilesales.ecommerce.database.AppDataBase

class OrderRepository(application: Application) {

    private val orderDao = AppDataBase.getDataBase(application).orderDao()

    fun loadAllByUser(userId : String) = orderDao.loadAllOrdersByUser(userId)
}