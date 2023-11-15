package com.example.upcyclingstore.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val IP_ADDRESS = "61.245.246.227:8089"
    lateinit var b : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_login)

        b.buttonLogin.setOnClickListener {
            val intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
        }
    }
}