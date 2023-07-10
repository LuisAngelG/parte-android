package com.sinche.victor.myapplication.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.sinche.victor.myapplication.R
import com.sinche.victor.myapplication.model.ApiResponse
import com.sinche.victor.myapplication.model.ApiResponseProduct
import com.sinche.victor.myapplication.model.ProductoAdapter
import com.sinche.victor.myapplication.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_product)

        imprimitProductos()
    }

    fun imprimitProductos() {

        val usuarioApi = RetrofitService.getUsuarioApi()
        val call = usuarioApi.getAllProduct()
        val listViewUsuarios = findViewById<ListView>(R.id.listViewProduct)

        call.enqueue(object : Callback<ApiResponseProduct> {
            override fun onResponse(call: Call<ApiResponseProduct>, response: Response<ApiResponseProduct>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val productos = apiResponse?.contenido

                    // Crear un adaptador para la ListView
                    val adapter = ProductoAdapter(this@ProductActivity, productos)

                    // Asignar el adaptador a la ListView
                    listViewUsuarios.adapter = adapter
                } else {
                    println("NO RECONOCE")
                }
            }

            override fun onFailure(call: Call<ApiResponseProduct>, t: Throwable) {
                println("ANDO CON SUEÑO")
            }
        })
    }


}
