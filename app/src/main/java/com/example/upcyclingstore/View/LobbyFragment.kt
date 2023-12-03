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
import com.example.upcyclingstore.Controller.ReceiveLobbyProduct
import com.example.upcyclingstore.Controller.ReceiveProductData
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.FragmentLobbyBinding
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 * Use the [LobbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
interface LobbyCallback {
    fun onFunctionCall(data: List<Lobby_Product_Adapter.LobbyItem>)
}

class LobbyFragment : Fragment(), LobbyCallback{
    lateinit var b : FragmentLobbyBinding
    private val images = listOf(
        R.drawable.upcycling,
        R.drawable.ic_launcher_foreground,
        R.drawable.header
    )
    override fun onFunctionCall(data: List<Lobby_Product_Adapter.LobbyItem>) {
        val lobby_adapter = Lobby_Product_Adapter(data)
        b.lobbyProduct.adapter = lobby_adapter
    }
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

        //리사이클러 아이템 배치 설정
        b.lobbyProduct.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val jsonData = JSONObject()
        jsonData.put("query", "SELECT * FROM Product")

        ReceiveLobbyProduct.receive(jsonData,requireContext(),this)
        return b.root
    }


}