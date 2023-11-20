package com.example.upcyclingstore.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.upcyclingstore.Controller.ImageSlideAdapter
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.FragmentLobbyBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LobbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class LobbyFragment : Fragment() {
    lateinit var b : FragmentLobbyBinding
    private val images = listOf(
        R.drawable.upcycling,
        R.drawable.ic_launcher_foreground,
        R.drawable.header
    )
    private val infiniteImageList = mutableListOf<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트 레이아웃 인플레이트
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_lobby, container, false)

        // ImageSlideAdapter 초기화 및 ViewPager2에 어댑터 설정
        val adapter = ImageSlideAdapter(requireContext(), images)  // this -> requiredContext() 변경
        b.viewPager2.adapter = adapter

        return b.root
    }


}