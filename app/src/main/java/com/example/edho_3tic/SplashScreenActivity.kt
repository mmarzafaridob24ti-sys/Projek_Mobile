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

        // Sudah login: langsung ke BaseActivity tanpa splash
        if (isLogin) {
            startActivity(Intent(this, BaseActivity::class.java))
            finish()
            return
        }

        // Belum login: splash 2 detik, lalu ke Login
        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            finish()
        }
    }
}