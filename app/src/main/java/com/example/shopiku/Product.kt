package com.example.shopiku

data class Product(
    val id: String,
    val name: String,
    val price: Int,
    val rating: Double,
    val image_url: String,
    val description: String
)