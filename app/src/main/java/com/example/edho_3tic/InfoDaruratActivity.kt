package com.example.edho_3tic

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.edho_3tic.databinding.ActivityInfoDaruratBinding
import com.google.android.material.tabs.TabLayoutMediator

// Halaman bertab: Tips Siaga dan Fasilitas
class InfoDaruratActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoDaruratBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInfoDaruratBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tinggi status bar dipakai sebagai padding header merah,
        // dan tinggi navigation bar sebagai padding bawah
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.headerArea.setPadding(0, bars.top, 0, 0)
            v.setPadding(bars.left, 0, bars.right, bars.bottom)
            insets
        }

        // Tombol kembali di toolbar
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener { finish() }

        // 1. Pasang adapter ke ViewPager2
        binding.viewPager.adapter = InfoDaruratTabsAdapter(this)

        // 2. Hubungkan TabLayout dan ViewPager2 (WAJIB setelah adapter dipasang).
        //    Menekan tab menggeser halaman, menggeser halaman memindahkan tab aktif
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tips Siaga"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_book)
                    // Badge tanpa angka (hanya titik) sebagai penanda informasi
                    tab.getOrCreateBadge().isVisible = true
                }
                1 -> {
                    tab.text = "Fasilitas"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_location)
                    // Badge dengan angka = jumlah fasilitas pada daftar
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = DataFasilitas.daftar.size
                }
            }
        }.attach()
    }
}