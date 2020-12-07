package com.example.pertemuan11_tugas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class BarangTambah : AppCompatActivity() {

    private lateinit var editBarangView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barang_tambah)

        //Mengambil ID editText pada layout activity_barang_tambah.xml
        editBarangView = findViewById(R.id.ed_namabarang)

        //Mengirim Data Intent jika editText telah diisi, jika kosong maka data intent berupa result canceled
        val button = findViewById<Button>(R.id.btn_simpan)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editBarangView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val namaBarang = editBarangView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, namaBarang)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}