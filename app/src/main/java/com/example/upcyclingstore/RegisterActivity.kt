package com.example.upcyclingstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var b : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this,R.layout.activity_register)
    }
}