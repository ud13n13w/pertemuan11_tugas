    package com.example.pertemuan11_tugas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Adapter digunakan untuk melakukan perulangan Item yang bersifat dinamis pada Item Layout (RecyclerView_item)
class BarangListAdapter internal constructor(context: Context) : RecyclerView.Adapter<BarangListAdapter.BarangViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list_barang = emptyList<Barang>()

    inner class BarangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val id_barang : TextView = itemView.findViewById(R.id.tv_isiid)
        val nama_barang : TextView = itemView.findViewById(R.id.tv_isinama)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item,parent, false)
        return BarangViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int){
        val current = list_barang[position]
        holder.id_barang.text = current.id_barang.toString()
        holder.nama_barang.text = current.nama_barang
    }

    internal fun setBarang(barang: List<Barang>) {
        this.list_barang = barang
        notifyDataSetChanged()
    }

    override fun getItemCount() = list_barang.size
}