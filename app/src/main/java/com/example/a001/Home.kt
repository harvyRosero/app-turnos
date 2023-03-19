package com.example.a001

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a001.adapter.TurnosAdapter
import java.text.SimpleDateFormat
import java.util.*

class Home : AppCompatActivity() {

    fun initRecyclerView(){
        val codigo = intent.getStringExtra("codigo").toString()
        val DB =  db(applicationContext)
        val listaDatos = DB.consultarTurnos(codigo)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TurnosAdapter(listaDatos)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initRecyclerView()

        val DB =  db(applicationContext)

        val nombre = intent.getStringExtra("nombre").toString()
        val codigo = intent.getStringExtra("codigo").toString()
        val empleo = intent.getStringExtra("empleo").toString()

        val tv_nombre: TextView =  findViewById(R.id.t_v_nombre)
        val tv_codigo: TextView =  findViewById(R.id.t_v_codigo)
        val tv_empleo: TextView =  findViewById(R.id.t_v_empleo)

        tv_nombre.setText(nombre)
        tv_codigo.setText(codigo)
        tv_empleo.setText(empleo)

        val btn_entrada: Button = findViewById(R.id.btn_entrada)
        val btn_salida: Button = findViewById(R.id.btn_salida)

        val sharedPreferences = getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val estado_btn_e = sharedPreferences.getBoolean("state_btn_e", true)
        val estado_btn_s = sharedPreferences.getBoolean("state_btn_s", false)

        btn_entrada.isEnabled = estado_btn_e
        btn_salida.isEnabled = estado_btn_s

        btn_entrada.setOnClickListener {
            val fechaHoraActual = Date()
            val formatoFechaHora = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val fechaHoraActualFormateada = formatoFechaHora.format(fechaHoraActual)

            editor.putString("fecha", fechaHoraActualFormateada)
            editor.putBoolean("state_btn_e", false)
            editor.putBoolean("state_btn_s", true)
            editor.apply()
            btn_entrada.isEnabled = false
            btn_salida.isEnabled= true
        }

        btn_salida.setOnClickListener {
            val fechaHoraActual = Date()
            val formatoFechaHora = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val fechaHoraActualFormateada = formatoFechaHora.format(fechaHoraActual)
            val valor = sharedPreferences.getString("fecha", "no se encontro")
            editor.putBoolean("state_btn_e", true)
            editor.putBoolean("state_btn_s", false)
            editor.apply()
            btn_entrada.isEnabled = true
            btn_salida.isEnabled = false
            try{
                DB.agregarTurno(codigo, "", valor, fechaHoraActualFormateada)
                Toast.makeText(this, "OK!", Toast.LENGTH_LONG).show()
            }catch (e: Exception){
                Toast.makeText(this, "Error turnos" + e, Toast.LENGTH_LONG).show()
            }

            initRecyclerView()
        }

    }
}