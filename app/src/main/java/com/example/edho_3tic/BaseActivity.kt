package com.example.edho_3tic

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.edho_3tic.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // bottom = 0 supaya tidak ada jarak kosong di bawah Bottom Navigation
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Beranda sebagai fragment awal
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.beranda -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.aktivitas -> {
                    replaceFragment(AktivitasFragment())
                    true
                }
                R.id.panduan -> {
                    replaceFragment(PanduanFragment())
                    true
                }
                R.id.profil -> {
                    replaceFragment(ProfilFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Tanpa addToBackStack: tombol kembali langsung keluar aplikasi
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}