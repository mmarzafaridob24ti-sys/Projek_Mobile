package com.example.edho_3tic

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.edho_3tic.data.entity.LaporanEntity
import com.example.edho_3tic.databinding.ItemAktivitasBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// DIUBAH: sekarang menampilkan LaporanEntity (data dari Room), bukan AktivitasModel.
// onHapus adalah fungsi dari Fragment: adapter hanya meminta penghapusan,
// sedangkan perintah ke database dijalankan oleh Fragment
class AktivitasAdapter(
    context: Context,
    private val items: List<LaporanEntity>,
    private val onHapus: (LaporanEntity) -> Unit
) : ArrayAdapter<LaporanEntity>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemAktivitasBinding.inflate(LayoutInflater.from(context), parent, false)
        val data = items[position]

        binding.ivIcon.setImageResource(ikonUntuk(data.jenis))
        binding.tvJenis.text = data.jenis
        binding.tvLokasi.text = data.lokasi
        binding.tvWaktu.text = formatWaktu(data.createdAt)
        binding.tvStatus.text = data.status

        // Catatan hanya tampil jika diisi
        if (data.catatan.isBlank()) {
            binding.tvCatatan.visibility = View.GONE
        } else {
            binding.tvCatatan.visibility = View.VISIBLE
            binding.tvCatatan.text = data.catatan
        }

        // Selesai = hijau, selain itu = merah
        binding.tvStatus.setTextColor(
            if (data.status == "Selesai") Color.parseColor("#2E9B6B")
            else ContextCompat.getColor(context, R.color.emergency_red)
        )

        // Klik laporan: ringkasan singkat
        binding.root.setOnClickListener {
            Snackbar.make(parent, "${data.jenis} di ${data.lokasi}: ${data.status}", Snackbar.LENGTH_SHORT).show()
        }

        // Klik ikon sampah: konfirmasi dulu, baru hapus dari database
        binding.btnHapus.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Hapus Laporan")
                .setMessage("Yakin ingin menghapus laporan ${data.jenis} ini?")
                .setPositiveButton("Ya") { _, _ -> onHapus(data) }
                .setNegativeButton("Batal", null)
                .show()
        }

        return binding.root
    }

    // Ikon mengikuti jenis kejadian
    private fun ikonUntuk(jenis: String): Int = when (jenis) {
        "Darurat Medis" -> R.drawable.ic_heart
        "Kebakaran" -> R.drawable.ic_fire
        "Kriminalitas" -> R.drawable.ic_shield
        else -> R.drawable.ic_warning
    }

    // Mengubah createdAt (milidetik) menjadi teks tanggal yang mudah dibaca
    private fun formatWaktu(milidetik: Long): String =
        SimpleDateFormat("d MMM yyyy, HH:mm", Locale.forLanguageTag("id-ID")).format(Date(milidetik))
}