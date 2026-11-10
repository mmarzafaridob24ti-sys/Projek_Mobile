package com.example.edho_3tic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        // Sudah login: langsung ke BaseActivity tanpa splash dan tutorial
        if (isLogin) {
            startActivity(Intent(this, BaseActivity::class.java))
            finish()
            return
        }

        // Belum login: tampilkan splash 2 detik
        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {
            delay(2000)

            // DIUBAH: cek apakah tutorial sudah pernah dilihat
            val tutorialSeen = getSharedPreferences("app_pref", MODE_PRIVATE)
                .getBoolean("tutorial_seen", false)

            // Belum pernah: ke Tutorial. Sudah pernah: langsung ke Login
            val tujuan = if (tutorialSeen) LoginActivity::class.java
            else TutorialActivity::class.java

            startActivity(Intent(this@SplashScreenActivity, tujuan))
            finish()
        }
    }
}