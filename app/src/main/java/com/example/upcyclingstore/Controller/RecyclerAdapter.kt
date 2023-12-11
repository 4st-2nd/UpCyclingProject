package com.example.upcyclingstore.Controller

import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.upcyclingstore.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class RecyclerAdapter(private val data: List<MyItem>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // 데이터 아이템의 모델 클래스
    data class MyItem(val title: String, val imagebm: Bitmap, val price: String,val nick: String)

    // 뷰홀더 클래스 정의
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.wasteName)
        val itemButton: Button = itemView.findViewById(R.id.btn_buy)
        val itemImageView: ImageView = itemView.findViewById(R.id.wasteImg)
        lateinit var title: String
        lateinit var price: String
        lateinit var nick: String
        lateinit var image: Bitmap
    }

    // onCreateViewHolder: 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_waste, parent, false)
        return ViewHolder(view)
    }

    // onBindViewHolder: 뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.title = item.title
        holder.image = item.imagebm
        holder.price = item.price
        holder.nick = item.nick
        // 데이터를 뷰에 바인딩
        holder.itemTextView.text = item.title
        holder.itemImageView.setImageBitmap(item.imagebm)

        // 버튼에 클릭 리스너 설정 등 추가 작업 가능
        holder.itemButton.setOnClickListener {
            showBottomSheetDialog(holder.itemView.context,holder)
        }
    }

    // getItemCount: 아이템 개수 반환
    override fun getItemCount(): Int {
        return data.size
    }

    private fun showBottomSheetDialog(context: Context,holder: ViewHolder) {
        val inflater = LayoutInflater.from(context)
        val bottomSheetView = inflater.inflate(R.layout.waste_bottomsheet, null)
        val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetView.findViewById<ImageView>(R.id.btn_close).setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetView.findViewById<TextView>(R.id.txt_ID).text = holder.nick
        bottomSheetView.findViewById<TextView>(R.id.txt_TITLE).text = holder.title
        bottomSheetView.findViewById<TextView>(R.id.txt_PRICE).text = holder.price + " ￦"
        bottomSheetView.findViewById<ImageView>(R.id.image).setImageBitmap(holder.image)

        bottomSheetDialog.show()
    }
}