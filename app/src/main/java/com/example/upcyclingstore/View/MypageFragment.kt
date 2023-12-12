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

        b.txtName.text = name.toString()

        b.txtOrder.setOnClickListener {
                val newFragment = OrderFragment()
                // 프래그먼트 트랜잭션 시작
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                // 트랜잭션에 새로운 프래그먼트 추가
                transaction.replace(R.id.flFragment, newFragment)
                // 백 스택에 추가하여 사용자가 뒤로가기 버튼을 눌렀을 때 이전 프래그먼트로 돌아갈 수 있도록 함
                transaction.addToBackStack("my")
                // 트랜잭션 커밋
                transaction.commit()
        }

        // Inflate the layout for this fragment
        return b.root
    }

}