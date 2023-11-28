package com.example.upcyclingstore.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.ActivityRegisterBinding
import com.example.upcyclingstore.Controller.SendDataToServer
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    lateinit var b: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_register)

        b.btnReg.setOnClickListener {
            val jsonData = JSONObject()
            jsonData.put("m_username", b.edtName.text.toString())
            jsonData.put("m_password", b.edtPass.text.toString())
            jsonData.put("m_email", b.edtEmail.text.toString())
            jsonData.put("m_date", b.edtDate.text.toString())

            var send = SendDataToServer()
            send.sendToServer(jsonData,applicationContext)
        }
    }



}