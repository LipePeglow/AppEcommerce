package com.mobilesales.ecommerce.fragment

import android.content.Intent
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
import com.mobilesales.ecommerce.UserLoginActivity
import com.mobilesales.ecommerce.adapter.OrderAdapter
import com.mobilesales.ecommerce.model.*
import com.mobilesales.ecommerce.viewModel.OrderViewModel
import com.mobilesales.ecommerce.viewModel.UserViewModel
import java.util.*

class OrderFragment : Fragment() {

    lateinit var recyclerOrder: RecyclerView
    lateinit var user : User

    private val orderViewModel by viewModels<OrderViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null)
            user = arguments?.getSerializable("USER") as User
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_order, container, false)

        recyclerOrder = view.findViewById(R.id.rv_order)

        val adapterOrder = OrderAdapter( requireContext())


            orderViewModel.getOrdersByUser(user.id).observe(viewLifecycleOwner, Observer { orders ->
                adapterOrder.list = orders
                adapterOrder.notifyDataSetChanged()
            })

            recyclerOrder.adapter = adapterOrder
            recyclerOrder.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

            return view
        }
    }


