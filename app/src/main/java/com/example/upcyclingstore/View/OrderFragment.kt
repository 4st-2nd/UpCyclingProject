package com.example.upcyclingstore.View

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upcyclingstore.Controller.ImageSlideAdapter
import com.example.upcyclingstore.Controller.Lobby_Product_Adapter
import com.example.upcyclingstore.Controller.OrderAdapter
import com.example.upcyclingstore.Controller.ReceiveLobbyProduct
import com.example.upcyclingstore.Controller.ReceiveOrder
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.FragmentLobbyBinding
import com.example.upcyclingstore.databinding.FragmentOrderBinding
import org.json.JSONObject

interface OrderCallback {
    fun onFunctionCall(data: List<OrderAdapter.MyItem>)
}

class OrderFragment : Fragment(), OrderCallback {
    lateinit var b: FragmentOrderBinding

    override fun onFunctionCall(data: List<OrderAdapter.MyItem>) {
        val lobby_adapter = OrderAdapter(data)
        b.order.adapter = lobby_adapter
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트 레이아웃 인플레이트
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)

        //리사이클러 아이템 배치 설정
        b.order.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return b.root
    }

    override fun onResume() {
        super.onResume()
        val context: Context = requireActivity()
        val prefs: SharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        val userID = prefs.getString("userID", "이름 오류")
        val jsonData = JSONObject()
        jsonData.put(
            "query",
            "SELECT Product.* FROM Product JOIN `Order` ON Product.productID = `Order`.productID WHERE `Order`.userID = $userID;"
        )
        Log.d("query",jsonData.get("query").toString())
        ReceiveOrder.receive(jsonData, requireContext(), this)
    }
}