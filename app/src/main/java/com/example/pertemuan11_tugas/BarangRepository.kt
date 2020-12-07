package com.example.pertemuan11_tugas

import androidx.lifecycle.LiveData

//Repository merupakan langkah untuk memisahkan antara operasi data dengan arsitektur
//Repository memisahkan hal tersebut dengan menggunakan API
//Repository ditujukan untuk mengelola query dan mengelola banyak data backend
//Repository adalah algoritma untuk memutuskan mengambil data dari jaringan atau local database(DAO)

class BarangRepository (private val barangDao : BarangDao){

    val allBarang: LiveData<List<Barang>> = barangDao.getAlphabetizedWords()

    suspend fun insert(barang: Barang){
        barangDao.insert(barang)
    }
    suspend fun deleteALL(){
        barangDao.deleteALL()
    }
}