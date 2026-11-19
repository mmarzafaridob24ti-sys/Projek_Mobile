package com.example.edho_3tic.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.edho_3tic.data.dao.LaporanDao
import com.example.edho_3tic.data.entity.LaporanEntity

// DATABASE: menyatukan Entity dan DAO. version = 1 (naikkan jika skema berubah)
@Database(
    entities = [LaporanEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // Pintu akses ke operasi tabel laporan
    abstract fun laporanDao(): LaporanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Singleton: database mahal dibuat berulang, jadi pakai satu instance saja
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "siaga_database"
                ).build().also { INSTANCE = it }
            }
    }
}