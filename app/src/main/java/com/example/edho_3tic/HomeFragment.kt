package com.example.edho_3tic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.edho_3tic.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Nama dan lokasi dari SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        binding.tvNama.text = sharedPref.getString("username", "Pengguna")
        binding.tvLokasi.text = DialogHelper.ambilLokasi(requireContext())

        // BARU: tekan kartu lokasi untuk mengubah lokasi saat ini
        binding.cardLokasi.setOnClickListener {
            DialogHelper.tampilInput(
                requireContext(),
                "Ubah Lokasi",
                binding.tvLokasi.text.toString()
            ) { lokasiBaru ->
                sharedPref.edit().putString("lokasi", lokasiBaru).apply()
                binding.tvLokasi.text = lokasiBaru
                tampilSnackbar("Lokasi diperbarui")
            }
        }

        binding.btnNotif.setOnClickListener { tampilSnackbar("Tidak ada notifikasi baru") }

        // SOS membuka Layanan Darurat (EnamActivity)
        binding.btnSos.setOnClickListener {
            startActivity(Intent(requireContext(), EnamActivity::class.java))
        }

        binding.cardAmbulans.setOnClickListener { tampilSnackbar("Menghubungi ambulans...") }
        binding.cardPemadam.setOnClickListener { tampilSnackbar("Menghubungi pemadam...") }
        binding.cardPolisi.setOnClickListener { tampilSnackbar("Menghubungi polisi...") }
    }

    private fun tampilSnackbar(pesan: String) {
        Snackbar.make(binding.root, pesan, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}