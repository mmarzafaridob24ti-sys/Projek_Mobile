package com.example.edho_3tic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.edho_3tic.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, 0)
            insets
        }

        // Toolbar (judul "SIAGA" sudah ada di dalam toolbar, jadi title bawaan disembunyikan)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Nama dari SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna")
        binding.tvNama.text = username
        Log.v("hasil", "Username: $username")

        binding.btnNotif.setOnClickListener { tampilSnackbar("Tidak ada notifikasi baru") }

        // Tombol SOS membuka EnamActivity (Layanan Darurat)
        binding.btnSos.setOnClickListener {
            startActivity(Intent(this@MainActivity, EnamActivity::class.java))
        }

        binding.cardAmbulans.setOnClickListener { tampilSnackbar("Menghubungi ambulans...") }
        binding.cardPemadam.setOnClickListener { tampilSnackbar("Menghubungi pemadam...") }
        binding.cardPolisi.setOnClickListener { tampilSnackbar("Menghubungi polisi...") }

        // Main -> Detail (Menu Opsi)
        binding.btnDetail.setOnClickListener {
            startActivity(Intent(this@MainActivity, DetailActivity::class.java))
        }
    }

    private fun tampilSnackbar(pesan: String) {
        Snackbar.make(binding.root, pesan, Snackbar.LENGTH_SHORT)
            .setAction("Tutup") { Log.e("Info Snackbar", "Snackbar ditutup") }
            .show()
    }
}