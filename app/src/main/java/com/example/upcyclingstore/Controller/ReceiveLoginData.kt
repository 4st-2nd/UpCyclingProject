package com.example.upcyclingstore.Controller

import android.content.Context
import android.widget.Toast
import com.example.upcyclingstore.View.LoginActivity
import com.example.upcyclingstore.View.LoginCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

class ReceiveLoginData {
    companion object {
        public fun receive(jsonData: JSONObject, context: Context,callback: LoginCallback) {

            val url = "http://61.245.246.227:8089/receive.php"
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
                    lateinit var jsonObject: JSONObject

                    if (responseBody != "null")
                        jsonObject = JSONObject(responseBody) //json 파싱
                    else {
                        jsonObject = JSONObject()
                        jsonObject.put("status", "NULL")
                    }

                    // UI 스레드에서 결과 처리
                    withContext(Dispatchers.Main)
                    {
                        if (jsonObject.has("status"))
                            Toast.makeText(context, "존재하지않는 계정입니다.", Toast.LENGTH_SHORT).show()
                        else
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