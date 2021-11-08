package com.mobilesales.ecommerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.R
import com.mobilesales.ecommerce.adapter.ProductCategoryAdapter
import com.mobilesales.ecommerce.model.ProductCategory
import com.mobilesales.ecommerce.repository.ProductsRepository
import com.mobilesales.ecommerce.viewModel.ProductViewModel


class ProductCategoryFragment : Fragment() {

    lateinit var recyclerCaterogy: RecyclerView
    private val productViewModel by viewModels<ProductViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val view: View = inflater.inflate(R.layout.fragment_product_category, container, false)

        recyclerCaterogy = view.findViewById(R.id.recyclerview_product_category)

        val adapterCategory = ProductCategoryAdapter( requireContext())

        productViewModel.allCategories.observe(viewLifecycleOwner, Observer{
            adapterCategory.list = it
            adapterCategory.notifyDataSetChanged()
        })

    recyclerCaterogy.adapter = adapterCategory
    recyclerCaterogy.layoutManager = GridLayoutManager(requireContext(), 2)

            return view
    }

    interface  Callback{
        fun itemSelector(category: ProductCategory)
    }
}

