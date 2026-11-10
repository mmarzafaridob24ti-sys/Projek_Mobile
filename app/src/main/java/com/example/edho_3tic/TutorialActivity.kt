package com.example.edho_3tic

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.edho_3tic.databinding.ActivityTutorialBinding

// BARU: halaman tutorial 3 fragment yang tampil setelah Splash
class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Daftar halaman tutorial (3 fragment)
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        // Pasang adapter ke ViewPager2
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialViewPager.adapter = adapter

        // Hubungkan titik penanda dengan ViewPager2.
        // WAJIB dipanggil SETELAH adapter dipasang
        binding.dotIndicator.attachTo(binding.tutorialViewPager)
    }

    // Dipanggil dari tombol Lewati (halaman 1-2) dan Mulai (halaman 3)
    fun selesaiTutorial() {
        // Simpan penanda "tutorial sudah dilihat".
        // Memakai file app_pref (bukan user_pref) agar tidak terhapus saat logout
        getSharedPreferences("app_pref", MODE_PRIVATE)
            .edit()
            .putBoolean("tutorial_seen", true)
            .apply()

        if (intent.getBooleanExtra("dari_profil", false)) {
            // Dibuka ulang dari menu Profil: cukup kembali ke Profil
            finish()
        } else {
            // Dibuka setelah Splash: lanjut ke halaman Login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}