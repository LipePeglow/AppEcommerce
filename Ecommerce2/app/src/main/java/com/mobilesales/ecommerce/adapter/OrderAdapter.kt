package com.mobilesales.ecommerce.adapter

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.ProductDetailActivity
import com.mobilesales.ecommerce.R
import com.mobilesales.ecommerce.model.Order
import com.mobilesales.ecommerce.model.Product
import java.util.*

class OrderAdapter ( val context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>(){

    var list: List<Order> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val order = list[position]
        holder.id.text = order.id
        holder.time.text = SimpleDateFormat("HH:mm:ss dd/mm/yyyy", Locale.getDefault()).format(Date(order.time))
        holder.status.text = order.status.message
        holder.metodo.text = order.method.message
        holder.price.text = order.price.toString()
    }

    override fun getItemCount(): Int = list.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id: TextView = itemView.findViewById(R.id.tv_id)
        val time: TextView = itemView.findViewById(R.id.tv_time)
        val status: TextView = itemView.findViewById(R.id.tv_status)
        val metodo: TextView = itemView.findViewById(R.id.tv_metodo)
        val price: TextView = itemView.findViewById(R.id.tv_total)

    }



}