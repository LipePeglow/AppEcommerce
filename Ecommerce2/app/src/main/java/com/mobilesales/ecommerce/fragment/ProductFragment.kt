package com.mobilesales.ecommerce.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.R
import com.mobilesales.ecommerce.adapter.ProductAdapter
import com.mobilesales.ecommerce.model.Product
import com.mobilesales.ecommerce.model.ProductCategory
import com.mobilesales.ecommerce.repository.ProductRepository

class ProductFragment : Fragment() {

    lateinit var recyclerProduct : RecyclerView
    lateinit var category : ProductCategory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       if (arguments != null){

            category = (arguments?.getSerializable("CATEGORY") as ProductCategory)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_product, container, false)

        recyclerProduct = view.findViewById(R.id.rv_product)

        val productRepository = ProductRepository(requireActivity().application)

        val adapterProduct = ProductAdapter(productRepository.loadProductByCategory(category.id), requireContext())

        recyclerProduct.adapter = adapterProduct

        recyclerProduct.layoutManager = GridLayoutManager(requireContext(),3)

        return view
    }
}