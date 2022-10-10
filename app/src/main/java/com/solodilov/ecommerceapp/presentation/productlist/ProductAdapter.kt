package com.solodilov.ecommerceapp.presentation.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.solodilov.ecommerceapp.R
import com.solodilov.ecommerceapp.databinding.ItemProductBinding
import com.solodilov.ecommerceapp.domain.entity.Product
import java.util.*

class ProductAdapter(
    private val onClick: (Product) -> Unit,
) : ListAdapter<Product, ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false),
            onClick
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val onClick: (Product) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {

        binding.apply {
            productName.text = product.title
            productPrice.text = itemView.context.getString(
                R.string.price_format,
                product.price
            )
        }

        Glide
            .with(itemView)
            .load(product.thumbnail)
            .into(binding.productImage)

        itemView.setOnClickListener { onClick(product) }
    }
}

private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}