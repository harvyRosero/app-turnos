package com.example.a001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FormValidate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_validate)

        val btn_ingresar: Button = findViewById(R.id.btn_ingresar)
        val et_codigo: EditText = findViewById(R.id.et_codigo)
        val DB =  db(applicationContext)

        btn_ingresar.setOnClickListener{
            val codigo = et_codigo.text.toString();

            try{
                val datos: List<String> = DB.consultarTrabajador(codigo, applicationContext);
                val name = datos[0]
                val empleo = datos[1]

                if(!datos.isEmpty()){
                    val intent = Intent(applicationContext, Home::class.java)
                    intent.putExtra("nombre", name)
                    intent.putExtra("codigo", codigo)
                    intent.putExtra("empleo", empleo)
                    startActivity(intent)

                }else{
                    Log.d("Error", "No se encontro empleado")
                }

            }catch (e: Exception){
                Toast.makeText(this, "ERROR: No se encontraron datos", Toast.LENGTH_LONG).show()
            }
        }
    }
}