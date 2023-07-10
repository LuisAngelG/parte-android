package com.sinche.victor.myapplication.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sinche.victor.myapplication.R
import com.sinche.victor.myapplication.model.Usuarios
import com.sinche.victor.myapplication.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    var buttonBack : Button? = null
    lateinit var btnRegister: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonBack = findViewById(R.id.btn_volver)
        buttonBack?.setOnClickListener { ViewMain() }

        val editTextNombre = findViewById<EditText>(R.id.editNombre)
        val editTextUsername = findViewById<EditText>(R.id.editUsername)
        val editTextEmail = findViewById<EditText>(R.id.editEmail)
        val editTextPassword = findViewById<EditText>(R.id.editPassword)
        val editTextConfirmarPassword = findViewById<EditText>(R.id.editConfirmarPassword)

        btnRegister = findViewById(R.id.btn_register_view)
        btnRegister.setOnClickListener {
            if (editTextConfirmarPassword.text.toString() == editTextPassword.text.toString()) {
                registrarUsuario(
                    editTextNombre.text.toString(),
                    editTextUsername.text.toString(),
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                )
            } else {
                println("Contraseñas Diferentes")
            }
        }
    }

    fun registrarUsuario(nombre: String, username: String, email: String, password: String) {
        val usuarioApi = RetrofitService.getUsuarioApi()
        val call = usuarioApi.postNewUser(Usuarios(nombre, username, email, password))

        call.enqueue(object : Callback<Usuarios> {
            override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                if (response.isSuccessful) {
                    val nuevoUsuario = response.body()
                    println("El usuario se ha registrado correctamente")
                    Toast.makeText(this@RegisterActivity, "Registro de Usuario Exitoso", Toast.LENGTH_SHORT).show()
                } else {
                    println("NO SE PUDO REGISTRAR")
                }
            }

            override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                // Manejar el error de la solicitud
            }
        })
    }
    private fun ViewMain() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}