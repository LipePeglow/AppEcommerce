package com.mobilesales.ecommerce.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.R
import com.mobilesales.ecommerce.adapter.CartAdapter
import com.mobilesales.ecommerce.adapter.ProductAdapter
import com.mobilesales.ecommerce.model.*

class CartFragment : Fragment (){

    lateinit var recyclerCart : RecyclerView
    lateinit var order : Order


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerCart = view.findViewById(R.id.rv_cart)

        val product1: Product = Product(
            "1","Camiseta 89",
            ProductCategory("id", "Camisetas"),
            "Camisseta Leve.",
            19.90,
            arrayListOf(
                ProductColor("1","Branco","#ffffff"),
                ProductColor("2","Preta","#000000")
            ),
            arrayListOf(
                ProductSize("1", "P",),
                ProductSize("1", "M")
            ),
            emptyList())


        val product2: Product = Product(
            "1","Calça Jeans",
            ProductCategory("id", "Calças"),
            "Calça com proteção para chuva.",
            39.90,
            arrayListOf(
                ProductColor("1","Branco","#ffffff"),
                ProductColor("2","Preta","#000000")
            ),
            arrayListOf(
                ProductSize("1", "G",),
                ProductSize("1", "GG")
            ),
            emptyList())

        val adapterCart = CartAdapter(arrayListOf(
            OrderedProduct("1", product1,3 ),
            OrderedProduct("1", product1,3 ),
            OrderedProduct("1", product1,3 ),
            OrderedProduct("1", product1,3 ),
            OrderedProduct("1", product1,3 ),
            OrderedProduct("2", product2, 5),
            OrderedProduct("1", product1,1 ),
            OrderedProduct("2", product2, 5),
            OrderedProduct("2", product2, 5),
            OrderedProduct("2", product2, 5),
            OrderedProduct("2", product2, 5),
            OrderedProduct("1", product1,1 ),
            OrderedProduct("1", product1,1 ),
            OrderedProduct("1", product1,1 ),
            OrderedProduct("1", product1,1 ),
            OrderedProduct("2", product2, 1)),
            requireContext())

        recyclerCart.adapter = adapterCart
        recyclerCart.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        return view
    }
}