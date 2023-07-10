package com.sinche.victor.myapplication.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sinche.victor.myapplication.R
import com.sinche.victor.myapplication.model.ApiResponse
import com.sinche.victor.myapplication.model.Usuarios
import com.sinche.victor.myapplication.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var btn_register  : Button;
    lateinit var btningresar: Button;
    var edit_username : EditText?= null;
    var edit_password : EditText?= null;


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_username = findViewById(R.id.userOrEmail)
        edit_password = findViewById(R.id.password)

        btningresar = findViewById<Button>(R.id.btningresar)
        btningresar.setOnClickListener {
            validarDatos()
        }

        btn_register = findViewById(R.id.btn_registrar)
        btn_register.setOnClickListener{
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    fun validarDatos() {
        val usuarioApi = RetrofitService.getUsuarioApi()
        val call = usuarioApi.getAllUser()

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val usuarios = apiResponse?.contenido

                    // Obtener los valores ingresados en los EditText
                    val emailIngresado = edit_username?.text.toString()
                    val passwordIngresado = edit_password?.text.toString()

                    // Comparar los valores ingresados con los obtenidos de la API
                    usuarios?.forEach { usuario ->
                        if (usuario.email == emailIngresado &&
                            usuario.password == passwordIngresado) {
                            println("Coinciden completamente")
                            Toast.makeText(this@MainActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@MainActivity, ProductActivity::class.java)
                            startActivity(intent)
                        } else {
                            println("NO LO ESTA LEYENDO")
                        }
                    }
                } else {
                    // Manejar la respuesta de error
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Manejar el error de la solicitud
            }
        })
    }


}
