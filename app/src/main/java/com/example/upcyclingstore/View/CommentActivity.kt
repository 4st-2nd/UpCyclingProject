package com.example.upcyclingstore.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upcyclingstore.Controller.CommentAdapter
import com.example.upcyclingstore.Controller.OrderAdapter
import com.example.upcyclingstore.Controller.ProductRecyclerAdapter
import com.example.upcyclingstore.Controller.ReceiveComment
import com.example.upcyclingstore.Controller.ReceiveProductData
import com.example.upcyclingstore.Controller.SendDataToServer
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.ActivityCommentBinding
import com.example.upcyclingstore.databinding.ActivityMainBinding
import org.json.JSONObject

interface CommentCallback {
    fun onFunctionCall(data: List<CommentAdapter.MyItem>)
}
class CommentActivity : AppCompatActivity(),CommentCallback {
    lateinit var b : ActivityCommentBinding
    override fun onFunctionCall(data: List<CommentAdapter.MyItem>) {
        val lobby_adapter = CommentAdapter(data)
        b.comment.adapter = lobby_adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_comment)

        b.comment.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val prefs = getSharedPreferences("session", MODE_PRIVATE)
        val userID = prefs.getString("userID", "이름 오류")
        val productID = intent.getStringExtra("productID")
        select(productID!!)

        b.btnComment.setOnClickListener {
            if(b.edtComment.text.toString() == "")
                Toast.makeText(this, "댓글을 입력 해주세요.", Toast.LENGTH_SHORT).show()
            else {
                val jsonQuery = JSONObject()
                //페이지에 다시 돌아와도 검색창에 텍스트가 있나 없나
                jsonQuery.put(
                    "query",
                    "INSERT INTO Comments (productID,userID,content) VALUES ('${productID}','${userID}','${b.edtComment.text.toString()}');"
                )
                SendDataToServer.send(jsonQuery)
                Toast.makeText(this, "댓글이 작성되었습니다.", Toast.LENGTH_SHORT).show()
                b.edtComment.setText("")
                select(productID!!)
            }
        }
    }
    private fun select(productID: String) {
        val jsonData = JSONObject()
        //페이지에 다시 돌아와도 검색창에 텍스트가 있나 없나
        jsonData.put("query", "SELECT Product.productID, Product.userID, Comments.content, User.name FROM Comments JOIN Product ON Product.productID = Comments.productID JOIN User ON User.userID = Comments.userID WHERE Comments.productID = $productID;")
        ReceiveComment.receive(jsonData, this, this)
    }
}