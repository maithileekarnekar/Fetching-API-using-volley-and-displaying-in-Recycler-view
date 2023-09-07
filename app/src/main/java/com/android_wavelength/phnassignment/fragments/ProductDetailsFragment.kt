package com.android_wavelength.phnassignment.fragments

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android_wavelength.phnassignment.databinding.ProductDetailsFragmentBinding
import com.android_wavelength.phnassignment.models.Product
import com.squareup.picasso.Picasso

class ProductDetailsFragment : Fragment() {

    private lateinit var binding : ProductDetailsFragmentBinding
    private var product: Product? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setupViews()
    }

    private fun initData() {
        if (arguments != null && requireArguments().containsKey(ARG_PRODUCT)) {
            product = requireArguments().getSerializable(ARG_PRODUCT) as Product
        }
    }
    private fun setupViews() {
        binding.txtProductName.text = product?.productTitle
        binding.txtProductDescription.text = product?.productDescription
        binding.txtProductPrice.text = product?.productPrice.toString()
        binding.txtProductBrand.text = product?.productBrand
        binding.txtProductCategory.text= product?.productCategory
        binding.txtProductDiscountPercentage.text = product?.productDiscountPercentage.toString()
        binding.txtProductRating.text = product?.productRating.toString()
        binding.txtProductThumbnail.text = product?.productThumbnail
        binding.txtProductStock.text = product?.productStock.toString()

        Picasso.get()
            .load(product?.productThumbnail)
            .into(binding.imgProduct)
    }

    companion object {
        private const val ARG_PRODUCT = "product"

        fun newInstance(product: Product): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle()
            args.putSerializable(ARG_PRODUCT,product)
            fragment.arguments = args
            return fragment
        }
    }
}