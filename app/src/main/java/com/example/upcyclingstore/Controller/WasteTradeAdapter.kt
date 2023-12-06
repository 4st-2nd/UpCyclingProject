package com.example.upcyclingstore.Controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.upcyclingstore.View.WasteFragment
import com.example.upcyclingstore.databinding.FragmentWasteBinding
import com.example.upcyclingstore.databinding.ItemWastetradeBinding

class WasteTradeAdapter(private val dataArrayList: ArrayList<WasteList>,private val buyClickListener: OnBuyClickListener): RecyclerView.Adapter<WasteTradeAdapter.WasteTradeViewHolder>(){

    data class WasteList(val imgRes: Int, val name: String)

    interface OnBuyClickListener {
        fun onBuyClick(data: WasteList)
    }

    class WasteTradeViewHolder(private val binding: ItemWastetradeBinding, private val clickListener: OnBuyClickListener): RecyclerView.ViewHolder(binding.root){
        fun bind(data: WasteList){
            binding.wasteImg.setImageResource(data.imgRes)
            binding.wasteName.text = data.name
            // 각 아이템의 버튼에 클릭 리스너 추가
            binding.btnBuy.setOnClickListener {
                clickListener.onBuyClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WasteTradeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWastetradeBinding.inflate(inflater,parent,false)
        return WasteTradeViewHolder(binding, buyClickListener)
    }

    override fun getItemCount(): Int {
        return dataArrayList.size
    }

    override fun onBindViewHolder(holder: WasteTradeViewHolder, position: Int) {
        val data =  dataArrayList[position]
        holder.bind(data)
    }
}