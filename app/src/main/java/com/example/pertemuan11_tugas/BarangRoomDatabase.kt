package com.example.pertemuan11_tugas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//Room database digunakan untuk mengelola penugasan SQLite Database
//Room database menggunakan DAO untuk meng-query Database

@Database(entities = arrayOf(Barang::class), version = 1, exportSchema = false)
public abstract class BarangRoomDatabase : RoomDatabase() {

    abstract fun barangDao(): BarangDao

    private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.barangDao())
                }
            }
        }

        suspend fun populateDatabase(barangDao: BarangDao) {
            // Menghapus Semua Data ketika dijalankan
            barangDao.deleteALL()

            // Menambahkan Sampel Data
            var barang = Barang(0,"Es Teh Jumbo")
            barangDao.insert(barang)
            barang = Barang(0,"Dancow Coklat Jumbo")
            barangDao.insert(barang)
            // TODO: Add your own words!
        }

    }

    //Digunakan agar database tetap digunakan dan tidak membuat baru jika terdapat pengoperasian database seperti CRUD
    companion object {

        @Volatile
        private var INSTANCE: BarangRoomDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): BarangRoomDatabase {
            val tempInstance = INSTANCE

            if (tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BarangRoomDatabase::class.java,
                    "db_cafe"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}