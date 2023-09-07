package com.android_wavelength.phnassignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android_wavelength.phnassignment.R
import com.android_wavelength.phnassignment.adapters.ProductsAdapter
import com.android_wavelength.phnassignment.databinding.ProductsFragmentBinding
import com.android_wavelength.phnassignment.models.Product
import com.google.gson.Gson

class ProductsFragment : Fragment() {

    private lateinit var binding : ProductsFragmentBinding
    private lateinit var productsAdapter: ProductsAdapter
    private var products : ArrayList<Product> = ArrayList<Product>()
    private lateinit var requestQueue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductsFragmentBinding.inflate(layoutInflater)

        initRecyclerViewAndAdapter()
        initRequestQueue()
        getProducts()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsAdapter = ProductsAdapter(products)
        binding.recyclerProducts.adapter = productsAdapter

        productsAdapter.onProductClickListener = object : ProductsAdapter.OnProductClickListener {
            override fun onProductClick(product: Product) {
                openProductDetailsFragment(product)
            }
        }

    }
    private fun openProductDetailsFragment(product: Product) {
        val productDetailsFragment = ProductDetailsFragment.newInstance(product)

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, productDetailsFragment, "ProductDetailsFragment")
            .addToBackStack(null)
            .commit()
    }

    private fun getProducts() {
        val req = JsonObjectRequest(
            Request.Method.GET,
            "https://dummyjson.com/products",
            null,
            {jsonObject ->

                Log.e("tag", jsonObject.toString())

                val gson = Gson()

                val productsArray = gson.fromJson<Array<Product>> (
                    jsonObject.getJSONArray("products").toString(),
                    Array<Product>::class.java
                )
                products.addAll(productsArray)
                productsAdapter.notifyDataSetChanged()
            },
            { error ->
                Log.e("tag","error")
            }
        )
        requestQueue.add(req)
    }

    private fun initRequestQueue() {
        requestQueue = Volley.newRequestQueue(requireContext())
    }


    private fun initRecyclerViewAndAdapter() {
        binding.recyclerProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        productsAdapter = ProductsAdapter(products)
        binding.recyclerProducts.adapter = productsAdapter
    }


}