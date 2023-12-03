package com.example.upcyclingstore.Controller

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.upcyclingstore.R

class Lobby_Product_Adapter(private val data: List<LobbyItem>) : RecyclerView.Adapter<Lobby_Product_Adapter.ViewHolder>() {

    // 데이터 아이템의 모델 클래스
    data class LobbyItem(val title: String, val description: String, val image: Bitmap, val review: Int, val score: Float, val price: Int)

    // 뷰홀더 클래스 정의
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_title: TextView = itemView.findViewById(R.id.lobby_product_title)
        val item_image: ImageView = itemView.findViewById(R.id.lobby_product_image)
        val item_price: TextView = itemView.findViewById(R.id.lobby_product_price)
        val item_review: TextView = itemView.findViewById(R.id.lobby_product_review)
        val item_score: TextView = itemView.findViewById(R.id.lobby_product_score)
    }

    // onCreateViewHolder: 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lobby_product, parent, false)
        return ViewHolder(view)
    }

    // onBindViewHolder: 뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        // 데이터를 뷰에 바인딩
        holder.item_title.text = item.title
        holder.item_image.setImageBitmap(item.image)
        holder.item_price.text = item.price.toString()
        holder.item_score.text = item.score.toString()
        holder.item_review.text = item.review.toString()

        // 버튼에 클릭 리스너 설정 등 추가 작업 가능

    }

    // getItemCount: 아이템 개수 반환
    override fun getItemCount(): Int {
        return data.size
    }

}