package com.example.upcyclingstore.Controller

import android.content.Context
import android.util.Log
import android.widget.Toast
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
    val url = "http://61.245.246.227:8089/register.php"
    public fun sendToServer(jsonData: JSONObject,context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                // HTTP 요청 생성
                val requestBody =
                    RequestBody.create("application/json; charset=utf-8".toMediaType(), jsonData.toString())
                val request = Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build()

                // 응답 받기
                val response = OkHttpClient().newCall(request).execute()
                val responseBody = response.body?.string() ?: ""

                // UI 스레드에서 결과 처리
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, responseBody, Toast.LENGTH_SHORT).show()
                    processResult(responseBody)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun processResult(result: String) {
    }
}