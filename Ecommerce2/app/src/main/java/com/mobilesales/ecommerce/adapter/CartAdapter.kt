package com.mobilesales.ecommerce.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mobilesales.ecommerce.R
import com.mobilesales.ecommerce.fragment.CartFragment
import com.mobilesales.ecommerce.model.OrderedProduct
import com.mobilesales.ecommerce.repository.ProductsRepository
import com.mobilesales.ecommerce.viewModel.CartViewModel


class CartAdapter (val context: Context) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    private val productsRepository = ProductsRepository(context.applicationContext as Application)

    var list: MutableList<OrderedProduct> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = list.size
    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val orderedProduct = list[position]
        holder.title.text = orderedProduct.product.title
        holder.image.setImageResource(R.drawable.camiseta_mockup)
        holder.color.text = orderedProduct.color
        holder.size.text = orderedProduct.size
        holder.quantity.text = orderedProduct.quantity.toString()

        holder.qtdUp.setOnClickListener {
            orderedProduct.quantity += 1
            CartViewModel.updateQuantity(orderedProduct.product, orderedProduct.quantity)
            holder.quantity.text = orderedProduct.quantity.toString()
            updatePriceHolder(holder, orderedProduct)
            (context as CartFragment.callBack).updateCart()
        }
        holder.qtdDown.setOnClickListener {
            orderedProduct.quantity -= 1

            if (orderedProduct.quantity > 0) {
                holder.quantity.text = orderedProduct.quantity.toString()
                updatePriceHolder(holder, orderedProduct)
            }
            (context as CartFragment.callBack).updateCart()
            notifyDataSetChanged()
        }

        productsRepository.loadThumbnail(orderedProduct.product, holder.image)

        updatePriceHolder(holder, orderedProduct)
    }


@SuppressLint("SetTextI18n")
private fun updatePriceHolder (holder: ViewHolder, orderProduct: OrderedProduct) {

holder.price.text = "R$ ${orderProduct.product.price * orderProduct.quantity}"
}

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.iv_product_image)
        val title: TextView = itemView.findViewById(R.id.tv_product_title)
        val color: TextView = itemView.findViewById(R.id.tv_color)
        val size: TextView = itemView.findViewById(R.id.tv_size)
        val price: TextView = itemView.findViewById(R.id.tv_price)
        val quantity: TextView = itemView.findViewById(R.id.tv_qtd)
        val qtdUp: ImageView = itemView.findViewById(R.id.iv_qtd_up)
        val qtdDown: ImageView = itemView.findViewById(R.id.iv_qtd_down)
        val cardview: CardView = itemView.findViewById(R.id.cardview_product_item)

    }

}