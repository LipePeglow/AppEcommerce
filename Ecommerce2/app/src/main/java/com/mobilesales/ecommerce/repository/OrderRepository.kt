package com.mobilesales.ecommerce.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.mobilesales.ecommerce.database.AppDataBase
import com.mobilesales.ecommerce.model.Order
import com.mobilesales.ecommerce.model.OrderWithOrderedProducts
import com.mobilesales.ecommerce.viewModel.CartViewModel

class OrderRepository(application: Application) {

    private val orderDao = AppDataBase.getDataBase(application).orderDao()

    private val firestore = FirebaseFirestore.getInstance()

    fun loadAllByUser(userId : String) : LiveData<List<Order>>{
        val livedata = MutableLiveData<List<Order>>()
        firestore.collection("users").document(userId)
            .collection("orders")
            .orderBy("time", Query.Direction.DESCENDING)
            .addSnapshotListener { snap, error ->

                if (error != null) return@addSnapshotListener
                livedata.value = snap?.toObjects(Order::class.java)

            }
        return livedata
    }

    fun place(fullOrder : OrderWithOrderedProducts){

        val userRef = firestore.collection("users").document(fullOrder.order.userId)
        val orderRef = userRef.collection("orders").document(fullOrder.order.id)

        orderRef.set(fullOrder.order).addOnSuccessListener {
            fullOrder.product.forEach { product ->
                orderRef.collection("ordered_products")
                    .document(product.orderedProductId).set(product)
            }
            CartViewModel.clear()
        }
    }
}