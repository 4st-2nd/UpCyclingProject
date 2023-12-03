package com.example.upcyclingstore.Controller

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.upcyclingstore.R
import com.example.upcyclingstore.View.LobbyCallback
import com.google.gson.JsonArray
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

class ReceiveLobbyProduct {
    companion object {
        public fun receive(jsonData: JSONObject, context: Context, callback: LobbyCallback) {

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
                    val jsonArray: JsonArray = JsonParser.parseString(responseBody).asJsonArray
                    withContext(Dispatchers.Main)
                    {
                        val data = mutableListOf<Lobby_Product_Adapter.LobbyItem>()
                        var image: Bitmap
                        for(i:Int in 0..<jsonArray.size())
                        {
                            if(jsonArray.get(i).asJsonObject.has("image") && !jsonArray.get(i).asJsonObject.get("image").isJsonNull)
                                image = base64ToBitmap(jsonArray.get(i).asJsonObject.get("image").toString())
                            else
                                image = ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)?.toBitmap()!!
                            data.add(Lobby_Product_Adapter.LobbyItem(
                                jsonArray.get(i).asJsonObject.get("title").toString(),
                                jsonArray.get(i).asJsonObject.get("description").toString(),
                                image,
                                jsonArray.get(i).asJsonObject.get("review").toString().replace("\"","").toInt(),
                                jsonArray.get(i).asJsonObject.get("score").toString().replace("\"","").toFloat(),
                                jsonArray.get(i).asJsonObject.get("price").toString().replace("\"","").toInt()
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

        private fun base64ToBitmap(base64String: String): Bitmap {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }


    }
}