package com.example.edho_3tic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.edho_3tic.data.AppDatabase
import com.example.edho_3tic.data.entity.LaporanEntity
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.launch

class LaporFragment : Fragment() {

    private val jenis = listOf("Darurat Medis", "Kebakaran", "Kriminalitas", "Bencana")
    private val ikon = listOf(
        R.drawable.ic_heart, R.drawable.ic_fire,
        R.drawable.ic_shield, R.drawable.ic_warning
    )
    private val idCard = listOf(
        R.id.cardMedis, R.id.cardKebakaran, R.id.cardKriminal, R.id.cardBencana
    )
    private var terpilih = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lapor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lokasi kejadian mengikuti lokasi saat ini dari Beranda
        (view.findViewById<View>(R.id.tvLokasiKejadian) as? TextView)?.text =
            DialogHelper.ambilLokasi(requireContext())

        val cards = idCard.map { view.findViewById<MaterialCardView>(it) }

        cards.forEachIndexed { i, card ->
            card.findViewById<ImageView>(R.id.ivIcon).setImageResource(ikon[i])
            card.findViewById<TextView>(R.id.tvJenis).text = jenis[i]
            card.setOnClickListener {
                terpilih = i
                tandaiPilihan(cards)
            }
        }
        tandaiPilihan(cards)

        // BARU: akses database Room
        val db = AppDatabase.getInstance(requireContext())

        val btnLanjutkan = view.findViewById<Button>(R.id.btnLanjutkan)
        btnLanjutkan.setOnClickListener {
            val lokasi = DialogHelper.ambilLokasi(requireContext())
            val catatan = view.findViewById<EditText>(R.id.etCatatan).text.toString().trim()

            // Cegah tombol ditekan dua kali saat proses simpan berjalan
            btnLanjutkan.isEnabled = false

            // Operasi database harus di dalam coroutine
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    // CREATE: simpan laporan ke tabel "laporan". insert() mengembalikan id baru
                    val idBaru = db.laporanDao().insert(
                        LaporanEntity(
                            jenis = jenis[terpilih],
                            lokasi = lokasi,
                            catatan = catatan
                        )
                    )

                    // Simpan jenis dan id laporan terakhir untuk ditampilkan di StatusFragment
                    requireContext()
                        .getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                        .edit()
                        .putString("jenis_laporan", jenis[terpilih])
                        .putLong("laporan_id", idBaru)
                        .apply()

                    // Lanjut ke StatusFragment
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, StatusFragment())
                        .commit()
                } catch (e: Exception) {
                    btnLanjutkan.isEnabled = true
                    Toast.makeText(
                        requireContext(), "Gagal menyimpan laporan", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    // Kartu yang dipilih diberi garis merah
    private fun tandaiPilihan(cards: List<MaterialCardView>) {
        val tebal = (2 * resources.displayMetrics.density).toInt()
        val merah = ContextCompat.getColor(requireContext(), R.color.emergency_red)
        cards.forEachIndexed { i, card ->
            card.strokeColor = merah
            card.strokeWidth = if (i == terpilih) tebal else 0
        }
    }
}