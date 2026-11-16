package com.example.edho_3tic

// Wadah data satu fasilitas darurat
data class FasilitasModel(
    val nama: String,      // nama fasilitas
    val jenis: String,     // rumah sakit, pemadam, kepolisian, dst.
    val jarak: String,     // jarak dari lokasi pengguna (data contoh)
    val telepon: String,   // nomor darurat yang dihubungi saat item ditekan
    val ikon: Int          // ikon dari res/drawable
)