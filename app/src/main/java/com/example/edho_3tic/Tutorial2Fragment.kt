package com.example.edho_3tic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

// BARU: halaman 2 tutorial, cara memakai tombol SOS
class Tutorial2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lewati: sama seperti halaman 1
        view.findViewById<Button>(R.id.btnLewati).setOnClickListener {
            (requireActivity() as TutorialActivity).selesaiTutorial()
        }
    }
}