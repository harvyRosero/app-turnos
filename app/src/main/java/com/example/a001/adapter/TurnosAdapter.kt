package com.example.a001.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a001.R
import com.example.a001.turnos

class TurnosAdapter(private val turnosList: List<turnos>) : RecyclerView.Adapter<TurnosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TurnosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TurnosViewHolder(layoutInflater.inflate(R.layout.item_turnos, parent, false))
    }

    override fun getItemCount(): Int = turnosList.size


    override fun onBindViewHolder(holder: TurnosViewHolder, position: Int) {
        val item = turnosList[position]
        holder.render(item)
    }


}