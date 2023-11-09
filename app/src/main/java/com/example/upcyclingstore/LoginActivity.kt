package com.example.upcyclingstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.databinding.ActivityLoginBinding
import com.example.upcyclingstore.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    lateinit var b : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this,R.layout.activity_login)

        b.buttonLogin.setOnClickListener {
            val intent = Intent(this,LobbyActivity::class.java)
            startActivity(intent)
        }
    }
}