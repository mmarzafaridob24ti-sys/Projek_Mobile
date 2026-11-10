package com.example.edho_3tic

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PanduanDaruratFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_panduan_darurat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DIUBAH: tiap tombol di GridLayout dihubungkan ke nomor teleponnya
        pasangTelepon(view, R.id.btnAmbulans, "119")
        pasangTelepon(view, R.id.btnPemadam, "113")
        pasangTelepon(view, R.id.btnPolisi, "110")
        pasangTelepon(view, R.id.btnBasarnas, "115")
        pasangTelepon(view, R.id.btnUmum, "112")
        pasangTelepon(view, R.id.btnPln, "123")
    }

    // BARU: saat tombol ditekan, buka aplikasi telepon dengan nomor sudah terisi.
    // ACTION_DIAL hanya membuka dialer (tidak menelepon langsung), jadi tanpa izin khusus
    private fun pasangTelepon(view: View, idTombol: Int, nomor: String) {
        view.findViewById<View>(idTombol).setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$nomor")))
        }
    }
}