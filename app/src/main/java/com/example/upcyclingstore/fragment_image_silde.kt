package com.example.upcyclingstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.example.upcyclingstore.databinding.ActivityMainBinding

class fragment_image_silde(val image : Int) : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        var images = getView()?.findViewById<ImageView>(R.id.imageslide)
        images!!.setImageResource(image)
    }
}