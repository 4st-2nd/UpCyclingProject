package com.example.upcyclingstore.View

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.FragmentLobbyBinding
import com.example.upcyclingstore.databinding.FragmentMypageBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MypageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MypageFragment : Fragment() {

    lateinit var b : FragmentMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)

        val context: Context = requireActivity()
        val prefs: SharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        val username = prefs.getString("username", "이름 오류")
        val name = prefs.getString("name", "이름 오류")
        val email = prefs.getString("email", "이름 오류")

        b.mypageId.text = "name : "+username.toString()
        b.mypageNick.text = "nickname : "+name.toString()
        b.mypageEmail.text = "email : "+email.toString()

        // Inflate the layout for this fragment
        return b.root
    }

}