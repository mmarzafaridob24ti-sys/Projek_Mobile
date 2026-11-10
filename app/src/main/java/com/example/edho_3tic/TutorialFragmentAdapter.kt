package com.example.edho_3tic

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// BARU: menghubungkan daftar fragment dengan ViewPager2
class TutorialFragmentAdapter(
    activity: FragmentActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(activity) {

    // Jumlah halaman = jumlah fragment dalam daftar
    override fun getItemCount(): Int = fragments.size

    // Fragment yang ditampilkan pada posisi tertentu
    override fun createFragment(position: Int): Fragment = fragments[position]
}