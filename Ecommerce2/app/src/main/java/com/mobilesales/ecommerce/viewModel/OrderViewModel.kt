package com.mobilesales.ecommerce.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilesales.ecommerce.model.Order
import com.mobilesales.ecommerce.model.OrderWithOrderedProducts
import com.mobilesales.ecommerce.repository.OrderRepository

class OrderViewModel (application: Application) : AndroidViewModel(application)  {

    private val orderRepository = OrderRepository(getApplication())

    fun getOrdersByUser(userId : String) = orderRepository.loadAllByUser(userId)

    fun place(fullOder : OrderWithOrderedProducts) = orderRepository.place(fullOder)
}