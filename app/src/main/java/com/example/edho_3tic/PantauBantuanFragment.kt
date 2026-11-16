package com.example.edho_3tic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

// BARU: halaman 3 tutorial (terakhir), tombol Mulai
class PantauBantuanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pantau_bantuan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mulai: menandai tutorial selesai, lalu lanjut ke Login
        view.findViewById<Button>(R.id.btnMulai).setOnClickListener {
            (requireActivity() as PengenalanActivity).selesaiTutorial()
        }
    }
}