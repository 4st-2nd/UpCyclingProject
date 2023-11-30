package com.example.upcyclingstore.View

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.Controller.ReceiveLoginData
import com.example.upcyclingstore.Controller.SendDataToServer
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.ActivityLoginBinding
import org.json.JSONObject

interface LoginCallback {
    fun onFunctionCall(data: JSONObject)
}
class LoginActivity : AppCompatActivity(),LoginCallback {
    override fun onFunctionCall(data: JSONObject) {
        val editor: SharedPreferences.Editor = getSharedPreferences("session", MODE_PRIVATE).edit()
        editor.putString("username", data.getString("username"))
        editor.putString("name", data.getString("name"))
        editor.putString("email", data.getString("email"))
        editor.apply()

        val intent = Intent(this, LobbyActivity::class.java)
        startActivity(intent)
    }
    lateinit var b : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_login)

        b.buttonLogin.setOnClickListener {
            if(b.btnName.text.toString() == "" || b.btnPw.text.toString() == "")
            {
                Toast.makeText(this, "이름과 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val jsonData = JSONObject()
                jsonData.put("query", "SELECT * FROM User WHERE email = '${b.btnName.text.toString()}' AND password = '${b.btnPw.text.toString()}'")

                ReceiveLoginData.receive(jsonData,applicationContext,this)
            }
        }
    }
}