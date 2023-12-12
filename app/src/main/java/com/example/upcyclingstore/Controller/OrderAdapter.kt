package com.example.upcyclingstore.Controller

import android.content.Intent
import android.graphics.Bitmap
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.upcyclingstore.R
import com.example.upcyclingstore.View.DetailActivity
import java.io.ByteArrayOutputStream

class OrderAdapter(private val data: List<MyItem>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    // 데이터 아이템의 모델 클래스
    data class MyItem(val title:String,val price:Int,val image:Bitmap)

    // 뷰홀더 클래스 정의
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemtitle: TextView = itemView.findViewById(R.id.txt_order_title)
        val itemprice: TextView = itemView.findViewById(R.id.txt_order_price)
        val itemImageView: ImageView = itemView.findViewById(R.id.order_image)
    }

    // onCreateViewHolder: 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    // onBindViewHolder: 뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        // 데이터를 뷰에 바인딩
        holder.itemtitle.text = item.title
        holder.itemprice.text = item.price.toString()
        holder.itemImageView.setImageBitmap(item.image)


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