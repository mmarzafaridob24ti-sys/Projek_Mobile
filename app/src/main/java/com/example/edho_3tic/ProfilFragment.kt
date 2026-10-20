package com.example.edho_3tic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.edho_3tic.databinding.FragmentProfilBinding

class ProfilFragment : Fragment() {
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // Isi tampilan awal
        binding.tvNama.text = sharedPref.getString("username", "Pengguna")

        binding.menuNama.ivIcon.setImageResource(R.drawable.ic_person)
        binding.menuNama.tvTitle.text = "Ubah Nama"
        binding.menuNama.tvSub.text = sharedPref.getString("username", "Pengguna")

        // ic_location berwarna putih, jadi diberi warna merah agar terlihat
        binding.menuLokasi.ivIcon.setImageResource(R.drawable.ic_location)
        binding.menuLokasi.ivIcon.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.emergency_red)
        )
        binding.menuLokasi.tvTitle.text = "Ubah Lokasi"
        binding.menuLokasi.tvSub.text = DialogHelper.ambilLokasi(requireContext())

        binding.menuTentang.ivIcon.setImageResource(R.drawable.ic_shield)
        binding.menuTentang.tvTitle.text = "Tentang SIAGA"
        binding.menuTentang.tvSub.text = "Versi 1.0"

        // Ubah nama
        binding.menuNama.root.setOnClickListener {
            DialogHelper.tampilInput(
                requireContext(),
                "Ubah Nama",
                sharedPref.getString("username", "") ?: ""
            ) { namaBaru ->
                sharedPref.edit().putString("username", namaBaru).apply()
                binding.tvNama.text = namaBaru
                binding.menuNama.tvSub.text = namaBaru
            }
        }

        // Ubah lokasi
        binding.menuLokasi.root.setOnClickListener {
            DialogHelper.tampilInput(
                requireContext(),
                "Ubah Lokasi",
                DialogHelper.ambilLokasi(requireContext())
            ) { lokasiBaru ->
                sharedPref.edit().putString("lokasi", lokasiBaru).apply()
                binding.menuLokasi.tvSub.text = lokasiBaru
            }
        }

        // Tentang SIAGA
        binding.menuTentang.root.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("SIAGA — Keselamatan dalam satu akses")
                .setMessage(
                    "SIAGA adalah aplikasi tanggap darurat yang membantu pengguna " +
                            "melaporkan kejadian, membagikan lokasi, dan memantau " +
                            "kedatangan petugas dengan cepat dan minim langkah."
                )
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // Logout: hapus SharedPreferences, kembali ke Login, bersihkan back stack
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit().clear().apply()
                    dialog.dismiss()

                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}