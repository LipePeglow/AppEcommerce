package com.mobilesales.ecommerce.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilesales.ecommerce.repository.OrderRepository

class OrderViewModel (application: Application) : AndroidViewModel(application)  {

    private val orderRepository = OrderRepository(getApplication())

    fun getOrdersByUser(userId : String) = orderRepository.loadAllByUser(userId)
}