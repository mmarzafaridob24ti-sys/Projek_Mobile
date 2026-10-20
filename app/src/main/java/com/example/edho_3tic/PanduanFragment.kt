package com.example.edho_3tic

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.edho_3tic.databinding.FragmentPanduanBinding

class PanduanFragment : Fragment() {
    private var _binding: FragmentPanduanBinding? = null
    private val binding get() = _binding!!

    // telp kosong = hanya panduan, telp terisi = bisa langsung dihubungi
    private val dataPanduan = listOf(
        mapOf("title" to "Henti Jantung", "desc" to "Tekan dada 100–120 kali per menit sambil menunggu ambulans", "telp" to ""),
        mapOf("title" to "Luka Bakar", "desc" to "Siram air mengalir selama 20 menit, jangan oleskan mentega", "telp" to ""),
        mapOf("title" to "Pendarahan", "desc" to "Tekan luka dengan kain bersih dan tinggikan bagian yang terluka", "telp" to ""),
        mapOf("title" to "Kebakaran", "desc" to "Merunduk di bawah asap, keluar, lalu hubungi pemadam", "telp" to ""),
        mapOf("title" to "Gempa Bumi", "desc" to "Berlindung di bawah meja kokoh dan jauhi kaca", "telp" to ""),
        mapOf("title" to "Ambulans", "desc" to "Ketuk untuk menghubungi 119", "telp" to "119"),
        mapOf("title" to "Pemadam Kebakaran", "desc" to "Ketuk untuk menghubungi 113", "telp" to "113"),
        mapOf("title" to "Polisi", "desc" to "Ketuk untuk menghubungi 110", "telp" to "110"),
        mapOf("title" to "Basarnas (SAR)", "desc" to "Ketuk untuk menghubungi 115", "telp" to "115")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPanduanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tombol ke halaman web (WebViewActivity), alamat dikirim lewat Intent
        binding.btnWeb.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            intent.putExtra("url", "https://esdm.jabarprov.go.id/prosedurevakuasi")
            startActivity(intent)
        }

        val adapter = SimpleAdapter(
            requireContext(),
            dataPanduan,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listPanduan.adapter = adapter

        binding.listPanduan.setOnItemClickListener { _, _, position, _ ->
            val item = dataPanduan[position]
            val telp = item["telp"] ?: ""
            if (telp.isNotEmpty()) {
                // Buka dialer (tidak butuh izin)
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telp")))
            } else {
                Toast.makeText(requireContext(), "Kamu memilih: ${item["title"]}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}