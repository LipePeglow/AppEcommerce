package com.mobilesales.ecommerce.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.R
import com.mobilesales.ecommerce.adapter.OrderAdapter
import com.mobilesales.ecommerce.model.*
import com.mobilesales.ecommerce.viewModel.OrderViewModel
import java.util.*

class OrderFragment : Fragment() {

    lateinit var recyclerOrder: RecyclerView
    private val orderViewModel by viewModels<OrderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_order, container, false)

        recyclerOrder = view.findViewById(R.id.rv_order)

        val adapterOrder = OrderAdapter( requireContext())
        orderViewModel.getOrdersByUser("").observe(viewLifecycleOwner, Observer{
            adapterOrder.list = it
            adapterOrder.notifyDataSetChanged()
        })

        recyclerOrder.adapter = adapterOrder
        recyclerOrder.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        return view
    }
}
