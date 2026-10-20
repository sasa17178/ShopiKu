package com.example.shopiku

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var rvProducts: RecyclerView

    // Tempelkan anon public Key Supabase kamu di dalam tanda kutip di bawah ini
    private val apiKey = "https://ksmfzehyoaojekhpcggd.supabase.co/rest/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi RecyclerView dengan tampilan Grid 2 Kolom
        rvProducts = findViewById(R.id.rvProducts)
        rvProducts.layoutManager = GridLayoutManager(this, 2)

        // Panggil fungsi untuk mengambil data produk dari Supabase
        fetchProducts()
    }

    private fun fetchProducts() {
        lifecycleScope.launch {
            try {
                // Memanggil endpoint rest/v1/products melalui Retrofit Client
                val response = RetrofitClient.instance.getProducts(apiKey, "Bearer $apiKey")

                if (response.isSuccessful && response.body() != null) {
                    val products = response.body()!!
                    rvProducts.adapter = ProductAdapter(products)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Gagal mengambil data: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Error Koneksi: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}