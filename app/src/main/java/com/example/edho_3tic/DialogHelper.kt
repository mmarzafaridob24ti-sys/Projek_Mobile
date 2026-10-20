package com.example.edho_3tic

import android.content.Context
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

object DialogHelper {

    const val LOKASI_DEFAULT = "Jl. Utama, Pekanbaru"

    // Membaca lokasi tersimpan (atau nilai awal jika belum pernah diubah)
    fun ambilLokasi(context: Context): String {
        val sharedPref = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        return sharedPref.getString("lokasi", LOKASI_DEFAULT) ?: LOKASI_DEFAULT
    }

    // Dialog dengan satu kolom isian
    fun tampilInput(
        context: Context,
        judul: String,
        nilaiAwal: String,
        onSimpan: (String) -> Unit
    ) {
        val edt = EditText(context)
        edt.setText(nilaiAwal)
        edt.setSelection(edt.text.length)

        val pad = (20 * context.resources.displayMetrics.density).toInt()
        val wadah = FrameLayout(context)
        wadah.setPadding(pad, pad / 2, pad, 0)
        wadah.addView(edt)

        AlertDialog.Builder(context)
            .setTitle(judul)
            .setView(wadah)
            .setPositiveButton("Simpan") { _, _ ->
                val teks = edt.text.toString().trim()
                if (teks.isEmpty()) {
                    Toast.makeText(context, "Isian tidak boleh kosong", Toast.LENGTH_SHORT).show()
                } else {
                    onSimpan(teks)
                }
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}