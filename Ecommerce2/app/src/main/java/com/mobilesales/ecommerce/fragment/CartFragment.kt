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
import com.mobilesales.ecommerce.adapter.ProductAdapter
import com.mobilesales.ecommerce.model.Order
import com.mobilesales.ecommerce.model.Product
import com.mobilesales.ecommerce.model.ProductCategory

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

        val adapterProduct = ProductAdapter(emptyList(),  requireContext())

        recyclerCart.adapter = adapterProduct

        recyclerCart.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        return view
    }
}