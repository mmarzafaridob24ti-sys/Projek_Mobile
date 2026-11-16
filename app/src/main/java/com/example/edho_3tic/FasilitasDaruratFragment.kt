package com.example.edho_3tic

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.edho_3tic.databinding.FragmentFasilitasDaruratBinding

// Tab "Fasilitas": menampilkan daftar fasilitas darurat dengan RecyclerView
class FasilitasDaruratFragment : Fragment() {
    private var _binding: FragmentFasilitasDaruratBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFasilitasDaruratBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adapter + aksi saat kartu ditekan: dialog konfirmasi lalu buka dialer
        val fasilitasAdapter = FasilitasAdapter(DataFasilitas.daftar) { fasilitas ->
            AlertDialog.Builder(requireContext())
                .setTitle(fasilitas.nama)
                .setMessage("${fasilitas.jenis} · ${fasilitas.jarak}\nHubungi nomor darurat ${fasilitas.telepon}?")
                .setPositiveButton("Hubungi") { _, _ ->
                    // ACTION_DIAL hanya membuka dialer, tanpa izin khusus
                    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${fasilitas.telepon}")))
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // RecyclerView WAJIB diberi layoutManager dan adapter
        binding.rvFasilitas.apply {
            // Grid 2 kolom (ganti LinearLayoutManager(requireContext()) untuk daftar 1 kolom)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = fasilitasAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // cegah kebocoran memori
    }
}