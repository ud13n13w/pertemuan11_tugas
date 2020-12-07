package com.example.pertemuan11_tugas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

//ViewModel digunakan untuk mengelola data yang dibutuhkan untuk ditampilkan kedalam Layout maupun Fragment

class BarangViewModel(application: Application) : AndroidViewModel(application) {

    //Repository
    private val repository: BarangRepository

    //LiveData digunakan untuk kondisi jika terdapat data yang dapat berubah-ubah pada layout (dinamis)
    //Sehingga jika terjadi perubahan data pada database, maka LiveData akan mengubah tampilan pada layout
        // sesuai dengan data yang telah diubah
    val allBarang : LiveData<List<Barang>>

    init {
        val BarangDao = BarangRoomDatabase.getDatabase(application, viewModelScope).barangDao()
        repository = BarangRepository(BarangDao)
        allBarang = repository.allBarang
    }

    //Method Wrapper untuk Repository Method Insert()
    fun insert(barang: Barang) = viewModelScope.launch {
        repository.insert(barang)
    }

    //Method Wrapper untuk Repository Method deleteALL()
    fun deleteALL() = viewModelScope.launch {
        repository.deleteALL()
    }

}