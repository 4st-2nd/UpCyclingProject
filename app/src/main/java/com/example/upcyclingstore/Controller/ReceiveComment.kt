package com.example.upcyclingstore.Controller

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.upcyclingstore.R
import com.example.upcyclingstore.View.CommentCallback
import com.example.upcyclingstore.View.LobbyCallback
import com.example.upcyclingstore.View.OrderCallback
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

class ReceiveComment {
    companion object {
        public fun receive(jsonData: JSONObject, context: Context, callback: CommentCallback) {

            val url = "http://61.245.246.227:8089/receive_array.php"
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    // HTTP 요청 생성
                    val requestBody =
                        RequestBody.create(
                            "application/json; charset=utf-8".toMediaType(),
                            jsonData.toString()
                        )
                    val request = Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build()

                    // 응답 받기
                    val response = OkHttpClient().newCall(request).execute()
                    val responseBody = response.body?.string()?: ""
                    Log.d("tag",responseBody)
                    //받아온 Json을 다시 배열로 초기화
                    val jsonArray: JsonArray = JsonParser.parseString(responseBody).asJsonArray
                    withContext(Dispatchers.Main)
                    {
                        //아이템으로 들어갈 데이터
                        val data = mutableListOf<CommentAdapter.MyItem>()
                        for(i:Int in 0..<jsonArray.size())
                        {
                            data.add(CommentAdapter.MyItem(
                                jsonArray.get(i).asJsonObject.getStringField("name"),
                                jsonArray.get(i).asJsonObject.getStringField("content"),
                            ))
                        }
                        callback.onFunctionCall(data)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return
        }

        //편의상 만들어둔 함수들
        private fun base64ToBitmap(base64String: String): Bitmap {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }
        fun JsonObject.getIntField(fieldName: String): Int {
            return this.get(fieldName)?.asString?.replace("\"", "")?.toInt() ?: 0
        }
        fun JsonObject.getFloatField(fieldName: String): Float {
            return this.get(fieldName)?.asString?.replace("\"", "")?.toFloat() ?: 0f
        }
        fun JsonObject.getStringField(fieldName: String): String {
            return this.get(fieldName)?.asString?.replace("\"", "")?.toString() ?: ""
        }


    }
}