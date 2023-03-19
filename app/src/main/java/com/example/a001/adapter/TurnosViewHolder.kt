package com.example.a001.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a001.R
import com.example.a001.turnos

class TurnosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val fecha_i = view.findViewById<TextView>(R.id.t_v_fecha_i)
    val fecha_f = view.findViewById<TextView>(R.id.t_v_fecha_f)
    fun render(TurnosModel: turnos){
        fecha_i.text = TurnosModel.fecha_i
        fecha_f.text = TurnosModel.fecha_f

    }
}