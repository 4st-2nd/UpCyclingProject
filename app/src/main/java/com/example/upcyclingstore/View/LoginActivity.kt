package com.example.upcyclingstore.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.Controller.ReceiveLoginData
import com.example.upcyclingstore.Controller.SendDataToServer
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.ActivityLoginBinding
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {

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
                jsonData.put("m_username", b.btnName.text.toString())
                jsonData.put("m_password", b.btnPw.text.toString())

                var receive = ReceiveLoginData()
                receive.ReceiveLoginData(jsonData,applicationContext)
                //val intent = Intent(this, LobbyActivity::class.java)
                //startActivity(intent)
            }
        }
    }
}