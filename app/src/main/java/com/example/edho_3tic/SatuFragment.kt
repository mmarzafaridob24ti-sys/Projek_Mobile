package com.example.edho_3tic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

class SatuFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_satu, container, false)
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

        view.findViewById<Button>(R.id.btnLanjutkan).setOnClickListener {
            // Simpan jenis laporan, lalu tampilkan DuaFragment (Status Bantuan)
            val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            sharedPref.edit().putString("jenis_laporan", jenis[terpilih]).apply()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DuaFragment())
                .commit()
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