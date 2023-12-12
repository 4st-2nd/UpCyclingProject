package com.example.upcyclingstore.View

import android.R.attr.defaultValue
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.Controller.SendDataToServer
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.ActivityDetailBinding
import org.json.JSONObject


class DetailActivity : AppCompatActivity(){
    lateinit var b : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val image_base64: String
        image_base64 = intent.getStringExtra("image").toString()
        val image:Bitmap = base64ToBitmap(image_base64)

        b.titleTxt.text = intent.getStringExtra("title")
        b.priceTxt.text = intent.getStringExtra("price") + " ￦"
        b.reviewTxt.text = intent.getStringExtra("review")
        b.scoreTxt.text = intent.getStringExtra("score")
        b.discriptionTxt.text = intent.getStringExtra("description")
        b.amountTxt.text = "남은 수량 : "+intent.getStringExtra("amount")
        b.itemPic.setImageBitmap(image)
        val productID = intent.getStringExtra("productID")

        val prefs = getSharedPreferences("session", MODE_PRIVATE)
        val userID = prefs.getString("userID", "이름 오류")
        b.backBtn.setOnClickListener {
            super.onBackPressed()
        }
        b.btnBuy.setOnClickListener {
            if(intent.getStringExtra("amount")!!.toInt() < 1)
                Toast.makeText(this, "매진된 상품입니다.", Toast.LENGTH_SHORT).show()
            else {
                val jsonData = JSONObject()
                val jsonQuery = JSONObject()
                Log.d("tag", "$productID $userID")
                jsonData.put(
                    "query",
                    "INSERT INTO `Order` (productID,userID) VALUES ('$productID','$userID')"
                )
                jsonQuery.put(
                    "query",
                    "UPDATE Product SET amount = amount -1 WHERE productID = $productID;"
                )
                Log.d("query", jsonData.get("query").toString())
                SendDataToServer.send(jsonData)
                SendDataToServer.send(jsonQuery)
                Toast.makeText(this, "상품 구매가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                super.onBackPressed()
            }
        }

        b.txtDetailComment.setOnClickListener {
            val intent = Intent(this, CommentActivity::class.java)
            intent.putExtra("productID",productID)
            startActivity(intent)
        }
    }

    private fun base64ToBitmap(base64String: String): Bitmap {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}