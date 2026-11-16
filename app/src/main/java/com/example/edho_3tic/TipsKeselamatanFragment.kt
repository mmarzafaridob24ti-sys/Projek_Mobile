package com.example.edho_3tic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// Tab "Tips Siaga": halaman informasi statis, cukup onCreateView()
class TipsKeselamatanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tips_keselamatan, container, false)
    }
}