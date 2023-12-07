package com.example.upcyclingstore.Controller

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.text.Layout
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.upcyclingstore.R
import com.example.upcyclingstore.View.DetailActivity
import com.example.upcyclingstore.View.RegisterActivity
import java.io.ByteArrayOutputStream

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
        val full:View = itemView.findViewById(R.id.full)
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
        holder.full.setOnClickListener {
            val intent = Intent(holder.full.context, DetailActivity::class.java)
            intent.putExtra("title",item.title)
            intent.putExtra("image",encodeImageToBase64(item.image))
            intent.putExtra("price",item.price.toString())
            intent.putExtra("score",item.score.toString())
            intent.putExtra("review",item.review.toString())
            intent.putExtra("description",item.description)
            holder.full.context.startActivity(intent)
        }

    }

    // getItemCount: 아이템 개수 반환
    override fun getItemCount(): Int {
        return data.size
    }
    private fun encodeImageToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

}