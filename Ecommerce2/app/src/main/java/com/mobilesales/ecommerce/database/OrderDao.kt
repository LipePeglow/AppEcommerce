package com.mobilesales.ecommerce.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mobilesales.ecommerce.model.Order
import com.mobilesales.ecommerce.model.OrderWithOrderedProducts

@Dao
interface OrderDao {

    @Query("SELECT * FROM `order` WHERE id = :orderId")
    fun loadOrderAndProductById(orderId: String): List<OrderWithOrderedProducts>
    @Query("SELECT * FROM `order` WHERE userId = :userId")
    fun loadOrderByUser(userId: String): List<Order>
    @Query("SELECT * FROM `order` WHERE userId = :userId")
    fun loadOrderAndProductByUser(userId: String): List<OrderWithOrderedProducts>


    @Insert
    fun  insert(order : Order)
    @Insert
    fun insertAl(vararg orders : Order)
    @Update
    fun update(order : Order)
}