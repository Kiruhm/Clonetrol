package com.example.clonetrol.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clonetrol.MainActivity
import com.example.clonetrol.R
import com.example.clonetrol.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.hide()
        supportActionBar?.hide()

        binding.btnLogIn.setOnClickListener {

            it.isClickable = false
            binding.loginStatusTextView.text = getText(R.string.login_1)

            Thread {
                Thread.sleep(3000)
                runOnUiThread { binding.loginStatusTextView.text = getText(R.string.login_2) }
                Thread.sleep(2000)
                runOnUiThread { binding.loginStatusTextView.text = getText(R.string.login_3) }
                Thread.sleep(1500)
                runOnUiThread {
                    moveToMainActivity()
                    it.isClickable = true
                }
            }.start()
        }
    }

    override fun onStart() {
        super.onStart()
        binding.loginStatusTextView.text = getText(R.string.log_in)

    }

    private fun moveToMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

}