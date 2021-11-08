package com.mobilesales.ecommerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.MainActivity
import com.mobilesales.ecommerce.ProductCategoryActivity
import com.mobilesales.ecommerce.R
import com.mobilesales.ecommerce.adapter.ProductCategoryAdapter
import com.mobilesales.ecommerce.model.ProductCategory
import com.mobilesales.ecommerce.repository.ProductRepository


class ProductCategoryFragment : Fragment() {

    lateinit var recyclerCaterogy: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val view: View = inflater.inflate(R.layout.fragment_product_category, container, false)

        recyclerCaterogy = view.findViewById(R.id.recyclerview_product_category)

        val productRepository = ProductRepository(requireActivity().application)

        val adapterCategory = ProductCategoryAdapter(productRepository.allCategories, requireContext())

    recyclerCaterogy.adapter = adapterCategory
    recyclerCaterogy.layoutManager = GridLayoutManager(requireContext(), 2)

            return view
    }

    interface  Callback{
        fun itemSelector(category: ProductCategory)
    }
}

