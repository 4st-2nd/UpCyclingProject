package com.example.upcyclingstore.Controller

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.example.upcyclingstore.R
import com.example.upcyclingstore.View.LoginCallback
import com.example.upcyclingstore.View.WasteCallback
import com.example.upcyclingstore.View.WasteFragment
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
import org.json.JSONArray
import org.json.JSONObject

class ReceiveProductData {
    companion object {
        public fun receive(jsonData: JSONObject, context: Context,callback: WasteCallback) {

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
                    val responseBody = response.body?.string() ?: ""
                    Log.d("check",responseBody)
                    val jsonArray: JsonArray = JsonParser.parseString(responseBody).asJsonArray

                    withContext(Dispatchers.Main)
                    {
                        val data = mutableListOf<RecyclerAdapter.MyItem>()
                        var image: Bitmap
                        for(i:Int in 0..jsonArray.size() - 1)
                        {
                            if(jsonArray.get(i).asJsonObject.has("image"))
                                image = base64ToBitmap(jsonArray.get(i).asJsonObject.get("image").toString())
                            else
                                image = getBitmapFromResource(context,R.drawable.ic_launcher_foreground)
                            data.add(RecyclerAdapter.MyItem(jsonArray.get(i).asJsonObject.get("title").toString(),  image))
                        }
                        callback.onFunctionCall(data)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return
        }

        private fun base64ToBitmap(base64String: String): Bitmap {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }

        fun getBitmapFromResource(context: Context, resourceId: Int): Bitmap {
            return BitmapFactory.decodeResource(context.resources, resourceId)
        }
    }
}