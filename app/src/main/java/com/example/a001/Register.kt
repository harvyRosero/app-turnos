package com.example.a001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.sql.SQLException

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btn_register: Button = findViewById(R.id.btn_register)
        val et_name: EditText = findViewById(R.id.et_name)
        val et_codigo: EditText = findViewById(R.id.et_codigo)
        val et_empleo: EditText = findViewById(R.id.et_empleo)

        val DB =  db(applicationContext)

        btn_register.setOnClickListener {
            val name = et_name.text.toString()
            val codigo = et_codigo.text.toString()
            val empleo = et_empleo.text.toString()

            if (name.isNotEmpty() && codigo.isNotEmpty() && empleo.isNotEmpty() ) {
                try{
                    DB.agregarTrabajador(codigo, name, empleo)
                    Toast.makeText(this, "Guardado!", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)

                }catch (e: Exception){
                    Toast.makeText(this, "Este usuario ya existe.", Toast.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(this, "debe llenar los campos!", Toast.LENGTH_LONG).show()
            }
        }
    }
}