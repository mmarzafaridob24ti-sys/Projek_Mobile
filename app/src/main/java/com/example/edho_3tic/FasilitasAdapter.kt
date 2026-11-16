package com.example.edho_3tic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edho_3tic.databinding.ItemFasilitasBinding

// Penghubung antara data (daftar fasilitas) dan tampilan satu kartu.
// onItemClick adalah fungsi dari Fragment: adapter hanya memanggilnya
// saat kartu ditekan, isi aksinya ditentukan oleh Fragment
class FasilitasAdapter(
    private val daftarFasilitas: List<FasilitasModel>,
    private val onItemClick: (FasilitasModel) -> Unit
) : RecyclerView.Adapter<FasilitasAdapter.FasilitasViewHolder>() {

    // ViewHolder: menyimpan binding satu kartu agar tidak dicari ulang tiap kali dipakai
    inner class FasilitasViewHolder(val binding: ItemFasilitasBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Dipanggil untuk membuat kartu baru dari item_fasilitas.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FasilitasViewHolder {
        val binding = ItemFasilitasBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FasilitasViewHolder(binding)
    }

    // Dipanggil setiap kartu perlu diisi data pada posisi tertentu.
    // Kartu didaur ulang, jadi SEMUA komponen harus diisi ulang di sini
    override fun onBindViewHolder(holder: FasilitasViewHolder, position: Int) {
        val item = daftarFasilitas[position]
        with(holder.binding) {
            ivIcon.setImageResource(item.ikon)
            tvNama.text = item.nama
            tvJenis.text = item.jenis
            tvJarak.text = item.jarak
            root.setOnClickListener { onItemClick(item) }
        }
    }

    // Jumlah total data
    override fun getItemCount(): Int = daftarFasilitas.size
}