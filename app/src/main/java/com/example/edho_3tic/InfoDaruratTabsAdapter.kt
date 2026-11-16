package com.example.edho_3tic

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// Mengatur fragment mana yang tampil untuk setiap tab
class InfoDaruratTabsAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    // Jumlah total tab. HARUS sama dengan jumlah case di createFragment(),
    // kalau lebih besar aplikasi crash "Posisi tidak valid"
    override fun getItemCount(): Int = 2

    // Fragment yang ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TipsKeselamatanFragment()
            1 -> FasilitasDaruratFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}