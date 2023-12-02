package com.example.upcyclingstore.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upcyclingstore.Controller.ImageSlideAdapter
import com.example.upcyclingstore.Controller.Lobby_Product_Adapter
import com.example.upcyclingstore.Controller.RecyclerAdapter
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

        //인기상품 리사이클러
        val data = mutableListOf<Lobby_Product_Adapter.LobbyItem>()
        data.add(Lobby_Product_Adapter.LobbyItem("test","설명",R.drawable.ic_launcher_foreground,5,4.5f,10000))
        data.add(Lobby_Product_Adapter.LobbyItem("test","설명",R.drawable.ic_launcher_foreground,5,4.5f,10000))
        data.add(Lobby_Product_Adapter.LobbyItem("test","설명",R.drawable.ic_launcher_foreground,5,4.5f,10000))
        data.add(Lobby_Product_Adapter.LobbyItem("test","설명",R.drawable.ic_launcher_foreground,5,4.5f,10000))
        data.add(Lobby_Product_Adapter.LobbyItem("test","설명",R.drawable.ic_launcher_foreground,5,4.5f,10000))
        data.add(Lobby_Product_Adapter.LobbyItem("test","설명",R.drawable.ic_launcher_foreground,5,4.5f,10000))

        //리사이클러 아이템 배치 설정
        b.lobbyProduct.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val lobby_adapter = Lobby_Product_Adapter(data)
        b.lobbyProduct.adapter = lobby_adapter

        return b.root
    }


}