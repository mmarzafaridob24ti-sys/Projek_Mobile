package com.example.edho_3tic.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// ENTITY: satu objek = satu baris pada tabel "laporan"
@Entity(tableName = "laporan")
data class LaporanEntity(
    // Primary key otomatis naik (1, 2, 3, ...)
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val jenis: String,                                  // jenis kejadian
    val lokasi: String,                                 // lokasi kejadian
    val catatan: String = "",                           // keterangan dari pengguna
    val status: String = "Diproses",                    // status penanganan
    val createdAt: Long = System.currentTimeMillis()    // waktu dibuat (milidetik)
)