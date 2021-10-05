package com.mobilesales.ecommerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.R
import com.mobilesales.ecommerce.model.ProductCategory

class ProductCategoryAdapter(val list: List<ProductCategory>, val context: Context) : RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: ProductCategory = list[position]
        holder.title.text = category.title

    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {

         val icon: ImageView = itemView.findViewById(R.id.img_category_icon)
         val title: TextView = itemView.findViewById(R.id.text_category_icon)
    }
}
