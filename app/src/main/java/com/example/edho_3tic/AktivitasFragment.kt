package com.example.edho_3tic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.edho_3tic.databinding.FragmentAktivitasBinding

class AktivitasFragment : Fragment() {
    private var _binding: FragmentAktivitasBinding? = null
    private val binding get() = _binding!!

    // Data riwayat laporan (20 item agar bisa di-scroll)
    private val daftar = listOf(
        AktivitasModel("Darurat Medis", "Jl. Utama, Pekanbaru", "Hari ini, 10:32", "Petugas menuju lokasi", R.drawable.ic_heart),
        AktivitasModel("Kebakaran", "Jl. Sudirman, Pekanbaru", "Kemarin, 19:15", "Selesai", R.drawable.ic_fire),
        AktivitasModel("Kriminalitas", "Jl. Soekarno-Hatta, Pekanbaru", "16 Okt, 21:40", "Selesai", R.drawable.ic_shield),
        AktivitasModel("Bencana", "Jl. Riau, Pekanbaru", "15 Okt, 07:05", "Selesai", R.drawable.ic_warning),
        AktivitasModel("Darurat Medis", "Jl. Tuanku Tambusai, Pekanbaru", "14 Okt, 14:20", "Selesai", R.drawable.ic_heart),
        AktivitasModel("Kebakaran", "Jl. Arifin Achmad, Pekanbaru", "13 Okt, 02:10", "Selesai", R.drawable.ic_fire),
        AktivitasModel("Kriminalitas", "Jl. Gajah Mada, Pekanbaru", "12 Okt, 23:05", "Selesai", R.drawable.ic_shield),
        AktivitasModel("Darurat Medis", "Jl. Hang Tuah, Pekanbaru", "11 Okt, 08:45", "Selesai", R.drawable.ic_heart),
        AktivitasModel("Bencana", "Jl. Kaharuddin Nasution, Pekanbaru", "10 Okt, 16:30", "Selesai", R.drawable.ic_warning),
        AktivitasModel("Kebakaran", "Jl. Teuku Umar, Pekanbaru", "9 Okt, 12:00", "Selesai", R.drawable.ic_fire),
        AktivitasModel("Darurat Medis", "Jl. Diponegoro, Pekanbaru", "8 Okt, 05:50", "Selesai", R.drawable.ic_heart),
        AktivitasModel("Kriminalitas", "Jl. Imam Munandar, Pekanbaru", "7 Okt, 20:15", "Selesai", R.drawable.ic_shield),
        AktivitasModel("Bencana", "Jl. Cut Nyak Dien, Pekanbaru", "6 Okt, 15:40", "Selesai", R.drawable.ic_warning),
        AktivitasModel("Darurat Medis", "Jl. Ahmad Yani, Pekanbaru", "5 Okt, 11:25", "Selesai", R.drawable.ic_heart),
        AktivitasModel("Kebakaran", "Jl. Paus, Pekanbaru", "4 Okt, 18:10", "Selesai", R.drawable.ic_fire),
        AktivitasModel("Kriminalitas", "Jl. Harapan Raya, Pekanbaru", "3 Okt, 22:30", "Selesai", R.drawable.ic_shield),
        AktivitasModel("Darurat Medis", "Jl. Pattimura, Pekanbaru", "2 Okt, 09:05", "Selesai", R.drawable.ic_heart),
        AktivitasModel("Bencana", "Jl. Yos Sudarso, Pekanbaru", "1 Okt, 13:50", "Selesai", R.drawable.ic_warning),
        AktivitasModel("Kebakaran", "Jl. Lintas Timur, Pekanbaru", "30 Sep, 03:20", "Selesai", R.drawable.ic_fire),
        AktivitasModel("Darurat Medis", "Jl. Datuk Setia Maharaja, Pekanbaru", "29 Sep, 17:45", "Selesai", R.drawable.ic_heart)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAktivitasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listAktivitas.adapter = AktivitasAdapter(requireContext(), daftar)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}