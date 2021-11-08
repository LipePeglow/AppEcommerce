package com.mobilesales.ecommerce.adapter

import android.content.Context
import android.content.Intent
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.ProductDetailActivity
import com.mobilesales.ecommerce.R
import com.mobilesales.ecommerce.model.Product

class ProductAdapter ( val context: Context) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(){
    var list: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = list[position]
        holder.title.text = product.title
        holder.imageView.setImageResource(R.drawable.camiseta_mockup)

        holder.cardview.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("PRODUCT", product)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.item_product_image)
        val title: TextView = itemView.findViewById(R.id.item_product_title)
        val cardview: CardView = itemView.findViewById(R.id.cardview_product_item)

    }

}