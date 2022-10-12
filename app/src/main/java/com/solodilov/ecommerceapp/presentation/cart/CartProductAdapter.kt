package com.solodilov.ecommerceapp.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solodilov.ecommerceapp.R
import com.solodilov.ecommerceapp.databinding.ItemCartProductBinding
import com.solodilov.ecommerceapp.domain.entity.CartProduct

class CartProductAdapter
    : ListAdapter<CartProduct, CartProductViewHolder>(CartProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder =
        CartProductViewHolder(ItemCartProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CartProductViewHolder(
    private val binding: ItemCartProductBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cartProduct: CartProduct) {

        binding.apply {
            productName.text = cartProduct.title
            productPrice.text = itemView.context.getString(
                R.string.price_format,
                cartProduct.price
            )
            stepper.itemCount.text = cartProduct.quantity.toString()
        }
    }
}

private class CartProductDiffCallback : DiffUtil.ItemCallback<CartProduct>() {

    override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
        return oldItem == newItem
    }
}