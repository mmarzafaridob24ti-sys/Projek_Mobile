package com.example.edho_3tic

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class DuaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dua, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Jenis laporan dari SatuFragment (nilai cadangan jika belum ada)
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val jenis = sharedPref.getString("jenis_laporan", "Darurat Medis")
        view.findViewById<TextView>(R.id.tvLaporan).text = "Laporan #SGA-240118 · $jenis"

        // Hubungi petugas: buka dialer nomor darurat 112
        view.findViewById<Button>(R.id.btnHubungi).setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:112")))
        }
    }
}