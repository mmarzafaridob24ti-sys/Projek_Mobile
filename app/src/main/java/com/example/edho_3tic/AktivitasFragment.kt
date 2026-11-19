package com.example.edho_3tic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.edho_3tic.data.AppDatabase
import com.example.edho_3tic.data.entity.LaporanEntity
import com.example.edho_3tic.databinding.FragmentAktivitasBinding
import kotlinx.coroutines.launch

class AktivitasFragment : Fragment() {
    private var _binding: FragmentAktivitasBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase

    // Semua laporan dari database (sebelum difilter chip)
    private var semuaLaporan: List<LaporanEntity> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAktivitasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        // Saat chip berganti, saring ulang daftar yang sudah dimuat dari database
        binding.chipGroupFilter.setOnCheckedStateChangeListener { _, _ ->
            terapkanFilter()
        }
    }

    // Dipanggil setiap tab dibuka dan saat kembali dari halaman lapor,
    // jadi laporan baru langsung muncul
    override fun onResume() {
        super.onResume()
        if (_binding != null) muatData()
    }

    // READ: ambil semua laporan dari Room, lalu tampilkan sesuai filter
    private fun muatData() {
        viewLifecycleOwner.lifecycleScope.launch {
            isiContohJikaPertama()
            semuaLaporan = db.laporanDao().getAll()
            terapkanFilter()
        }
    }

    // DELETE: hapus satu laporan dari Room, lalu muat ulang daftar
    private fun hapusLaporan(laporan: LaporanEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            db.laporanDao().delete(laporan)
            muatData()
        }
    }

    // Menyaring semuaLaporan sesuai chip yang aktif
    private fun terapkanFilter() {
        val hasil = when (binding.chipGroupFilter.checkedChipId) {
            R.id.chipMedis -> semuaLaporan.filter { it.jenis == "Darurat Medis" }
            R.id.chipKebakaran -> semuaLaporan.filter { it.jenis == "Kebakaran" }
            R.id.chipKriminal -> semuaLaporan.filter { it.jenis == "Kriminalitas" }
            R.id.chipBencana -> semuaLaporan.filter { it.jenis == "Bencana" }
            else -> semuaLaporan // chip "Semua"
        }
        tampilkan(hasil)
    }

    private fun tampilkan(data: List<LaporanEntity>) {
        binding.listAktivitas.adapter = AktivitasAdapter(requireContext(), data) { laporan ->
            hapusLaporan(laporan)
        }
        binding.tvJumlah.text = "Menampilkan ${data.size} laporan"

        // Teks kosong: beda pesan untuk "belum ada laporan" dan "tidak cocok dengan filter"
        if (data.isEmpty()) {
            binding.tvKosong.visibility = View.VISIBLE
            binding.tvKosong.text = if (semuaLaporan.isEmpty())
                "Belum ada laporan.\nTekan tombol SOS di Beranda untuk membuat laporan."
            else
                "Tidak ada laporan untuk filter ini."
        } else {
            binding.tvKosong.visibility = View.GONE
        }
    }

    // Data contoh: dimasukkan SEKALI saja agar daftar langsung terisi.
    // Penandanya disimpan di app_pref supaya tidak diisi ulang setelah dihapus
    private suspend fun isiContohJikaPertama() {
        val pref = requireContext().getSharedPreferences("app_pref", Context.MODE_PRIVATE)
        if (pref.getBoolean("laporan_contoh_dimuat", false)) return

        val sekarang = System.currentTimeMillis()
        val jam = 60 * 60 * 1000L
        val contoh = listOf(
            LaporanEntity(jenis = "Kebakaran", lokasi = "Jl. Sudirman, Pekanbaru", catatan = "Asap tebal dari ruko", status = "Selesai", createdAt = sekarang - 26 * jam),
            LaporanEntity(jenis = "Darurat Medis", lokasi = "Jl. Hang Tuah, Pekanbaru", catatan = "Warga pingsan di pinggir jalan", status = "Selesai", createdAt = sekarang - 50 * jam),
            LaporanEntity(jenis = "Kriminalitas", lokasi = "Jl. Gajah Mada, Pekanbaru", catatan = "Pencurian sepeda motor", status = "Selesai", createdAt = sekarang - 74 * jam),
            LaporanEntity(jenis = "Bencana", lokasi = "Jl. Riau, Pekanbaru", catatan = "Banjir setinggi lutut", status = "Selesai", createdAt = sekarang - 98 * jam),
            LaporanEntity(jenis = "Darurat Medis", lokasi = "Jl. Diponegoro, Pekanbaru", catatan = "Kecelakaan lalu lintas", status = "Selesai", createdAt = sekarang - 122 * jam),
            LaporanEntity(jenis = "Kebakaran", lokasi = "Jl. Paus, Pekanbaru", catatan = "Kebakaran lahan kosong", status = "Selesai", createdAt = sekarang - 146 * jam),
            LaporanEntity(jenis = "Kriminalitas", lokasi = "Jl. Imam Munandar, Pekanbaru", catatan = "", status = "Selesai", createdAt = sekarang - 170 * jam),
            LaporanEntity(jenis = "Darurat Medis", lokasi = "Jl. Ahmad Yani, Pekanbaru", catatan = "Sesak napas", status = "Selesai", createdAt = sekarang - 194 * jam)
        )
        contoh.forEach { db.laporanDao().insert(it) }
        pref.edit().putBoolean("laporan_contoh_dimuat", true).apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}