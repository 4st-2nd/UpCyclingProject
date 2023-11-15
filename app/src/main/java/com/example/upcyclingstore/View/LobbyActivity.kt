package com.example.upcyclingstore.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.Controller.ImageSlideAdapter
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.ActivityLobbyBinding


class LobbyActivity : AppCompatActivity() {
    lateinit var b : ActivityLobbyBinding
    private val images = listOf(
        R.drawable.upcycling,
        R.drawable.ic_launcher_foreground,
        R.drawable.header
    )
    private val infiniteImageList = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_lobby)

        val adapter = ImageSlideAdapter(this, images)
        b.viewPager2.adapter = adapter

    }
}
