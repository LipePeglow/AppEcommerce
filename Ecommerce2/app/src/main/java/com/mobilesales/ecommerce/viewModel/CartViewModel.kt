package com.mobilesales.ecommerce.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mobilesales.ecommerce.model.Order
import com.mobilesales.ecommerce.model.OrderedProduct
import com.mobilesales.ecommerce.model.ProductVariants
import java.util.*

class CartViewModel(application: Application) : AndroidViewModel(application) {

    val cartPrice = MutableLiveData<Double>()

    val orderedProducts = MutableLiveData<MutableList<OrderedProduct>>()

    companion object {
        val order = Order(
            time = Date().time,
            status = Order.Status.CART,
            method = Order.Method.NOME,
            userId = "0"
        )

        private val orderedProducts = mutableListOf<OrderedProduct>()


        private fun compare(product: ProductVariants): Boolean {

            var validateColor = false
            var validateSize = false

            orderedProducts.forEach { order ->
                if (order.product.id == product.product.id) {
                }
                product.colors.forEach {
                    if (it.checked)
                        validateColor = (order.color == it.name)

                }
                product.sizes.forEach {
                    if (it.checked)
                        validateSize = (order.color == it.size)
                }
                return validateColor && return validateSize
            }
            return false
        }
    }
}