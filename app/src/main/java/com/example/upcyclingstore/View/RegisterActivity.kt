package com.example.upcyclingstore.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.ActivityRegisterBinding
import com.example.upcyclingstore.Controller.SendDataToServer
import org.json.JSONObject

interface RegisterCallback {
    fun onFunctionCall()
}
class RegisterActivity : AppCompatActivity(), RegisterCallback {
    override fun onFunctionCall() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    lateinit var b: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_register)

        b.btnReg.setOnClickListener {
            if(b.edtName.text.toString() == "" || b.edtPass.text.toString() == "" || b.edtEmail.text.toString() == "" || b.edtNick.text.toString() == "")
                Toast.makeText(this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
            else {
                val jsonData = JSONObject()
                jsonData.put(
                    "query",
                    "INSERT INTO User (username,password,email,name) VALUES ('${b.edtName.text.toString()}','${b.edtPass.text.toString()}','${b.edtEmail.text.toString()}','${b.edtNick.text.toString()}')"
                )

                SendDataToServer.send(jsonData, this)
            }
        }
    }



}