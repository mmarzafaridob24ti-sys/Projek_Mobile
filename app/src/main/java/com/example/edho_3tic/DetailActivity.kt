package com.example.edho_3tic

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.edho_3tic.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Toolbar + tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Menu Opsi"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        binding.tvNama.text = sharedPref.getString("username", "Pengguna")

        // Isi baris menu
        binding.menuProfil.ivIcon.setImageResource(R.drawable.ic_person)
        binding.menuProfil.tvTitle.text = "Profil & Data Medis"
        binding.menuProfil.tvSub.text = "Kelola informasi darurat"

        binding.menuKontak.ivIcon.setImageResource(R.drawable.ic_phone)
        binding.menuKontak.tvTitle.text = "Kontak Darurat"
        binding.menuKontak.tvSub.text = "3 kontak tersimpan"

        binding.menuPanduan.ivIcon.setImageResource(R.drawable.ic_shield)
        binding.menuPanduan.tvTitle.text = "Panduan Keselamatan"
        binding.menuPanduan.tvSub.text = "Pertolongan pertama"

        binding.menuBantuan.ivIcon.setImageResource(R.drawable.ic_help)
        binding.menuBantuan.tvTitle.text = "Pusat Bantuan"
        binding.menuBantuan.tvSub.text = "FAQ dan dukungan"

        binding.menuProfil.root.setOnClickListener { pesan("Profil & Data Medis") }
        binding.menuKontak.root.setOnClickListener { pesan("Kontak Darurat") }
        binding.menuPanduan.root.setOnClickListener { pesan("Panduan Keselamatan") }
        binding.menuBantuan.root.setOnClickListener { pesan("Pusat Bantuan") }

        // Logout: hapus SharedPreferences lalu kembali ke Login
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    private fun pesan(nama: String) {
        Toast.makeText(this, "$nama dibuka", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}