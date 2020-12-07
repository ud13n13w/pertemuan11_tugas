package com.example.pertemuan11_tugas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var barangViewModel: BarangViewModel
    private val barangTambahRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Settting RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BarangListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Setting ViewModel
        barangViewModel = ViewModelProvider(this).get(BarangViewModel::class.java)
        barangViewModel.allBarang.observe(this, Observer { barang ->
            barang?.let { adapter.setBarang(it) }
        })

        //ketika tombol FAB diclick
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity,BarangTambah::class.java)
            startActivityForResult(intent,barangTambahRequestCode)

        }

        //Ketika tombol Hapus diclick
        val del = findViewById<Button>(R.id.btn_hapus)
        del.setOnClickListener{
            barangViewModel.deleteALL()
            Toast.makeText(applicationContext, "Data telah dihapus", Toast.LENGTH_LONG).show()

        }

    }

    //Action ketika activity menerima result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        //Mengambil data dari intent dan meng-insert kepada database
        if (requestCode == barangTambahRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(BarangTambah.EXTRA_REPLY)?.let{
                val barang = Barang(0,it)
                barangViewModel.insert(barang)
            }
        }
        else {
            Toast.makeText(applicationContext, "", Toast.LENGTH_LONG).show()
        }
    }
}