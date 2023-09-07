package com.android_wavelength.phnassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android_wavelength.phnassignment.R
import com.android_wavelength.phnassignment.models.Product
import com.bumptech.glide.Glide

class ProductsAdapter (
    private val products : ArrayList<Product>
        ): RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>(){

    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }

    var onProductClickListener : OnProductClickListener? = null

            inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
               val imgProduct : ImageView
               val txtProductName : TextView
               val txtProductDescription : TextView
               val txtProductPrice : TextView
               val txtProductBrand : TextView
               val txtProductThumbnail : TextView

               init {
                   imgProduct = view.findViewById(R.id.imgProduct)
                   txtProductName = view.findViewById(R.id.txtProductName)
                   txtProductDescription = view.findViewById(R.id.txtProductDescription)
                   txtProductPrice = view.findViewById(R.id.txtProductPrice)
                   txtProductBrand = view.findViewById(R.id.txtProductBrand)
                   txtProductThumbnail = view.findViewById(R.id.txtProductThumbnail)

                   view.setOnClickListener {

                       val product = products[adapterPosition]
                       onProductClickListener?.onProductClick(product)
                   }
               }

            }

    override fun getItemCount() = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.products_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ProductViewHolder, position: Int) {
        val product = products[position]

        holder.txtProductName.text = product.productTitle
        holder.txtProductDescription.text = product.productDescription
        holder.txtProductPrice.text = product.productPrice.toString()
        holder.txtProductBrand.text = product.productBrand
        holder.txtProductThumbnail.text = product.productThumbnail

        Glide.with(holder.imgProduct)
            .load(product.productThumbnail)
            .into(holder.imgProduct)
    }
}