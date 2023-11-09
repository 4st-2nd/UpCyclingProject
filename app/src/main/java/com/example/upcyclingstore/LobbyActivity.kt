package com.example.upcyclingstore

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.upcyclingstore.databinding.ActivityLobbyBinding


class LobbyActivity : AppCompatActivity() {
    lateinit var b : ActivityLobbyBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_lobby)
        var vp_slider = b.viewpager

        val pagerAdapter = ScreenSlidePagerAdapter(this)
            vp_slider.adapter = pagerAdapter
    }
}

private class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> fragment_image_silde(R.drawable.ic_launcher_foreground)
            1 -> fragment_image_silde(R.drawable.ic_launcher_foreground)
            else -> fragment_image_silde(R.drawable.ic_launcher_foreground)
        }
    }
}
