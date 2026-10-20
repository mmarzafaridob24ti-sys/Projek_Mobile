package com.example.edho_3tic

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.edho_3tic.databinding.ActivityEnamBinding

class EnamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEnamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Toolbar + tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Layanan Darurat"
            subtitle = "Lapor, pantau, dan panduan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // Menampilkan fragment pertama secara default
        if (savedInstanceState == null) {
            replaceFragment(SatuFragment())
        }

        // Setiap tombol menampilkan fragment-nya
        binding.btnFragment1.setOnClickListener { replaceFragment(SatuFragment()) }
        binding.btnFragment2.setOnClickListener { replaceFragment(DuaFragment()) }
        binding.btnFragment3.setOnClickListener { replaceFragment(TigaFragment()) }
    }

    // Tanpa addToBackStack: tombol kembali langsung menutup activity ini
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    // Tombol kembali di toolbar: langsung kembali ke Main
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}