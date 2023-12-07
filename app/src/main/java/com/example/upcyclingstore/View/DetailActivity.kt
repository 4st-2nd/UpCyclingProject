package com.example.upcyclingstore.View

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.Controller.Lobby_Product_Adapter
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.ActivityDetailBinding
import com.example.upcyclingstore.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity(){
    lateinit var b : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val image_base64: String
        image_base64 = intent.getStringExtra("image").toString()
        val image:Bitmap = base64ToBitmap(image_base64)

        b.titleTxt.text = intent.getStringExtra("title")
        b.priceTxt.text = intent.getStringExtra("price")
        b.reviewTxt.text = intent.getStringExtra("review")
        b.scoreTxt.text = intent.getStringExtra("score")
        b.discriptionTxt.text = intent.getStringExtra("description")
        b.itemPic.setImageBitmap(image)

        b.backBtn.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun base64ToBitmap(base64String: String): Bitmap {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}