package com.example.edho_3tic.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.edho_3tic.data.entity.LaporanEntity

// DAO: daftar operasi yang boleh dilakukan aplikasi terhadap tabel laporan.
// Fungsi "suspend" harus dipanggil dari coroutine agar tidak memblokir layar
@Dao
interface LaporanDao {

    // READ: ambil semua laporan, yang terbaru di atas
    @Query("SELECT * FROM laporan ORDER BY createdAt DESC")
    suspend fun getAll(): List<LaporanEntity>

    // CREATE: simpan satu laporan, mengembalikan id baris yang baru dibuat
    @Insert
    suspend fun insert(laporan: LaporanEntity): Long

    // DELETE: hapus laporan berdasarkan primary key
    @Delete
    suspend fun delete(laporan: LaporanEntity)
}