package com.example.edho_3tic

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class TigaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tiga, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isiBaris(view, R.id.menuAmbulans, R.drawable.ic_heart, "Ambulans", "119", "119")
        isiBaris(view, R.id.menuPemadam, R.drawable.ic_fire, "Pemadam Kebakaran", "113", "113")
        isiBaris(view, R.id.menuPolisi, R.drawable.ic_shield, "Polisi", "110", "110")
        isiBaris(view, R.id.menuBasarnas, R.drawable.ic_warning, "Basarnas (SAR)", "115", "115")
    }

    // Mengisi satu baris dan membuka dialer saat ditekan
    private fun isiBaris(
        view: View, idBaris: Int, ikon: Int,
        judul: String, nomor: String, telp: String
    ) {
        val baris = view.findViewById<View>(idBaris)
        baris.findViewById<ImageView>(R.id.ivIcon).setImageResource(ikon)
        baris.findViewById<TextView>(R.id.tvTitle).text = judul
        baris.findViewById<TextView>(R.id.tvSub).text = "Hubungi $nomor"
        baris.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telp")))
        }
    }
}