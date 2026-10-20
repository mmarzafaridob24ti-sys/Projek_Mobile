package com.example.edho_3tic

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.edho_3tic.databinding.ItemAktivitasBinding
import com.google.android.material.snackbar.Snackbar

class AktivitasAdapter(
    context: Context,
    private val items: List<AktivitasModel>
) : ArrayAdapter<AktivitasModel>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemAktivitasBinding.inflate(LayoutInflater.from(context), parent, false)
        val data = items[position]

        binding.ivIcon.setImageResource(data.ikon)
        binding.tvJenis.text = data.jenis
        binding.tvLokasi.text = data.lokasi
        binding.tvWaktu.text = data.waktu
        binding.tvStatus.text = data.status

        // Selesai = hijau, selain itu = merah
        binding.tvStatus.setTextColor(
            if (data.status == "Selesai") Color.parseColor("#2E9B6B")
            else ContextCompat.getColor(context, R.color.emergency_red)
        )

        // Aksi klik dipasang di adapter
        binding.root.setOnClickListener {
            Snackbar.make(parent, "${data.jenis} di ${data.lokasi}: ${data.status}", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }
}