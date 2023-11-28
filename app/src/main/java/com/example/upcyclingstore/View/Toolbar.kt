package com.example.upcyclingstore.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.ActivityLobbyBinding
import com.example.upcyclingstore.databinding.ActivityToolbarBinding

class toolbar : AppCompatActivity() {

    lateinit var b : ActivityToolbarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this,R.layout.activity_toolbar)


    }

}