package com.example.pertemuan11_tugas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "tbl_barang") digunakan untuk menunjukkan tabel yg digunakan pada database
@Entity(tableName = "tbl_barang")
data class Barang (

    //Digunakan untuk menunjukkan kolom apa pada database
    @PrimaryKey(autoGenerate = true) @ColumnInfo val id_barang : Int = 0,
    @ColumnInfo val nama_barang : String
)