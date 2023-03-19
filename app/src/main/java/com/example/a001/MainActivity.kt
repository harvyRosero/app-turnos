package com.example.a001
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.Toast
import java.sql.DriverManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)

        val usuario = "freedb_harvy"
        val contrasena = "S#q4c5n*D23Nk!V"
        val url2 = "jdbc:mysql://sql.freedb.tech:3306/freedb_pruebasklotin?user=freedb_harvy&password=S#q4c5n*D23Nk!V"

        button1.setOnClickListener {
            println("click")
            val intent = Intent(this, Register::class.java)
            startActivity(intent)

            val politic = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(politic)
            Class.forName("com.mysql.cj.jdbc.Driver")

            try{
                //val conexion = DriverManager.getConnection(url2)
                //val sentencia = conexion.createStatement()
                println("echo")
            }catch (e: Exception){
                println(e)
            }

        }

        button2.setOnClickListener {
            val intent = Intent(this, FormValidate::class.java)
            startActivity(intent)
        }

    }

}