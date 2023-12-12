package com.example.upcyclingstore.Controller

import android.graphics.Bitmap
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.upcyclingstore.R
import java.io.ByteArrayOutputStream

class CommentAdapter(private val data: List<MyItem>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    // 데이터 아이템의 모델 클래스
    data class MyItem(val name:String,val comment:String)

    // 뷰홀더 클래스 정의
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemname: TextView = itemView.findViewById(R.id.txt_comment_name)
        val itemcomment: TextView = itemView.findViewById(R.id.txt_comment)

    }

    // onCreateViewHolder: 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return ViewHolder(view)
    }

    // onBindViewHolder: 뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        // 데이터를 뷰에 바인딩
        holder.itemname.text = item.name
        holder.itemcomment.text = item.comment

    }
    // getItemCount: 아이템 개수 반환
    override fun getItemCount(): Int {
        return data.size
    }

}