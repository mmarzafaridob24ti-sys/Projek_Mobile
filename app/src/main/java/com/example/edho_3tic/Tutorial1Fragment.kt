package com.example.edho_3tic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

// BARU: halaman 1 tutorial, pengenalan SIAGA
class Tutorial1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lewati: menutup tutorial dan lanjut ke halaman berikutnya di alur aplikasi
        view.findViewById<Button>(R.id.btnLewati).setOnClickListener {
            (requireActivity() as TutorialActivity).selesaiTutorial()
        }
    }
}