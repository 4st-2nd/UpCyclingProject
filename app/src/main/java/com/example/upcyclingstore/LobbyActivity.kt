package com.example.upcyclingstore

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.upcyclingstore.databinding.ActivityLobbyBinding


class LobbyActivity : AppCompatActivity() {
    lateinit var b : ActivityLobbyBinding
    private val images = listOf(R.drawable.upcycling, R.drawable.ic_launcher_foreground, R.drawable.header)
    private val infiniteImageList = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_lobby)

        val adapter = ImageSlideAdapter(this, images)
        b.viewPager2.adapter = adapter

    }
}
