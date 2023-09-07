package com.android_wavelength.phnassignment.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product (
    val id : Int,
    @SerializedName("title")
    val productTitle : String?,
    @SerializedName("description")
    val productDescription : String?,
    @SerializedName("price")
    val productPrice : Int,
    @SerializedName("discountPercentage")
    val productDiscountPercentage : Double,
    @SerializedName("rating")
    val productRating : Double,
    @SerializedName("stock")
    val productStock : Int,
    @SerializedName("brand")
    val productBrand : String?,
    @SerializedName("category")
    val productCategory: String?,
    @SerializedName("thumbnail")
    val productThumbnail : String?,
    @SerializedName("images")
    val productImages : ArrayList<String>?
        ) : Serializable