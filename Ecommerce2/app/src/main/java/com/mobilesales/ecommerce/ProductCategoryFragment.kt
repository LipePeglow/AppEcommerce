package com.mobilesales.ecommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.adapter.ProductCategoryAdapter
import com.mobilesales.ecommerce.model.ProductCategory


class ProductCategoryFragment : Fragment() {

    lateinit var recyclerCaterogy: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val view: View = inflater.inflate(R.layout.fragment_product_category, container, false)


        recyclerCaterogy = view.findViewById(R.id.recyclerview_product_category)

        val arrayCategory = arrayListOf<ProductCategory>(
        ProductCategory("1", "Camisetas"),
        ProductCategory("2", "Calças"),
        ProductCategory("3", "Meias"),
        ProductCategory("4", "Tênis"),
        ProductCategory("5", "Casacos")
    )

        val adapterCategory = ProductCategoryAdapter(arrayCategory, requireContext())

    recyclerCaterogy.adapter = adapterCategory
    recyclerCaterogy.layoutManager = GridLayoutManager(requireContext(), 2)

            return view
    }
}

