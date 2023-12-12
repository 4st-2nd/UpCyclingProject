package com.example.upcyclingstore.Controller

import android.content.Context
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
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.ByteArrayOutputStream

class ProductRecyclerAdapter(private val data: List<MyItem>) : RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>() {

    // 데이터 아이템의 모델 클래스
    data class MyItem(val title: String, val description: String, val image: Bitmap, val review: Int, val score: Float, val price: Int, val name: String,val amount: Int,val productID: Int)

    // 뷰홀더 클래스 정의
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.wasteName)
        val itemButton: Button = itemView.findViewById(R.id.btn_buy)
        val itemImageView: ImageView = itemView.findViewById(R.id.wasteImg)
        val full:View = itemView.findViewById(R.id.productFull)
    }

    // onCreateViewHolder: 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_waste, parent, false)
        return ViewHolder(view)
    }

    // onBindViewHolder: 뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        // 데이터를 뷰에 바인딩
        holder.itemTextView.text = item.title
        holder.itemImageView.setImageBitmap(item.image)

        // 버튼에 클릭 리스너 설정 등 추가 작업 가능
        holder.itemButton.setOnClickListener {
            val intent = Intent(holder.full.context, DetailActivity::class.java)
            intent.putExtra("title",item.title)
            intent.putExtra("image",encodeImageToBase64(item.image))
            intent.putExtra("price",item.price.toString())
            intent.putExtra("score",item.score.toString())
            intent.putExtra("review",item.review.toString())
            intent.putExtra("description",item.description)
            intent.putExtra("name",item.name)
            intent.putExtra("amount",item.amount.toString())
            intent.putExtra("productID",item.productID.toString())
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