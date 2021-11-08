package com.mobilesales.ecommerce.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.R
import com.mobilesales.ecommerce.adapter.ProductAdapter
import com.mobilesales.ecommerce.model.Product
import com.mobilesales.ecommerce.model.ProductCategory
import com.mobilesales.ecommerce.repository.ProductsRepository
import com.mobilesales.ecommerce.viewModel.ProductViewModel

class ProductFragment : Fragment() {

    lateinit var recyclerProduct : RecyclerView
    lateinit var category : ProductCategory
    private  val productViewmodel by viewModels<ProductViewModel>()

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

        val adapterProduct = ProductAdapter(requireContext())

        productViewmodel.getProductsByCategory(category.id).observe(viewLifecycleOwner, Observer{
            adapterProduct.list = it
            adapterProduct.notifyDataSetChanged()
        })

        recyclerProduct.adapter = adapterProduct

        recyclerProduct.layoutManager = GridLayoutManager(requireContext(),3)

        return view
    }
}