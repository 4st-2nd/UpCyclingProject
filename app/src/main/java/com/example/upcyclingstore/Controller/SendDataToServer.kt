package com.example.upcyclingstore.Controller

import android.content.Context
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import com.example.upcyclingstore.R
import com.example.upcyclingstore.View.LoginCallback
import com.example.upcyclingstore.View.RegisterCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import org.json.JSONTokener

class SendDataToServer {
    companion object {
        public fun send(jsonData: JSONObject, context: Context, callback: RegisterCallback) {
            val url: String = "http://61.245.246.227:8089/send.php"
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val jsonString = jsonData.toString()
                    val utf8Bytes = jsonString.toByteArray(Charsets.UTF_8)
                    // HTTP 요청 생성
                    val requestBody =
                        RequestBody.create(
                            "application/json; charset=utf-8".toMediaType(),
                            utf8Bytes
                        )
                    val request = Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build()

                    // 응답 받기
                    val response = OkHttpClient().newCall(request).execute()
                    val responseBody = response.body?.string() ?: ""

                    val jsonObject = JSONObject(responseBody) //json 파싱
                    // UI 스레드에서 결과 처리
                    withContext(Dispatchers.Main) {
                        if (jsonObject.has("status"))
                            callback.onFunctionCall()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return
        }
    }
}
