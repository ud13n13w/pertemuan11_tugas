package com.example.pertemuan11_tugas

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Dao digunakan untuk pengoperasian query database dengan cara pemanggilan method
@Dao
interface BarangDao {
    @Query("SELECT * from tbl_barang ORDER BY id_barang ASC")
    fun getAlphabetizedWords(): LiveData<List<Barang>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(barang : Barang)

    @Query("DELETE FROM tbl_barang")
    suspend fun deleteALL()
}