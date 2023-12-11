package com.example.upcyclingstore.View

import android.app.Activity
import com.example.upcyclingstore.databinding.FragmentWriteProductBinding
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.upcyclingstore.Controller.SendDataToServer
import com.example.upcyclingstore.Controller.SendProductToServer
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.FragmentLobbyBinding
import com.example.upcyclingstore.databinding.FragmentMypageBinding
import org.json.JSONObject
import java.io.ByteArrayOutputStream


/**
 * A simple [Fragment] subclass.
 * Use the [MypageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

interface WriteProductCallback {
    fun onFunctionCall()
}
class WriteProductFragment : Fragment(),WriteProductCallback {
    override fun onFunctionCall() {
        val newFragment = ProductFragment()
        // 프래그먼트 트랜잭션 시작
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        // 트랜잭션에 새로운 프래그먼트 추가
        transaction.replace(R.id.flFragment, newFragment)
        // 백 스택에 추가하여 사용자가 뒤로가기 버튼을 눌렀을 때 이전 프래그먼트로 돌아갈 수 있도록 함
        transaction.addToBackStack("product")
        // 트랜잭션 커밋
        transaction.commit()
    }

    lateinit var b : FragmentWriteProductBinding
    private val PICK_IMAGE_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_write_product, container, false)
        b.btnImageUpload.setOnClickListener {
            openGallery()
        }
        b.btnUpload.setOnClickListener {
            if(b.edtTitle.text.toString() == "" || b.edtPrice.text.toString() == "" || b.edtDescription.text.toString() == "")
                Toast.makeText(requireContext(), "상품 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
            else
            {
                val bitmap: Bitmap? = getBitmapFromImageView(b.image)
                val base64String: String? = encodeBitmapToBase64(bitmap)
                val jsonData = JSONObject()
                jsonData.put(
                    "title",
                    b.edtTitle.text.toString()
                )
                jsonData.put(
                    "price",
                    b.edtPrice.text.toString()
                )
                jsonData.put(
                    "description",
                    b.edtDescription.text.toString()
                )
                jsonData.put(
                    "image",
                    base64String
                )
                SendProductToServer.send(jsonData, this)
            }
        }

        return b.root
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            b.image.setImageURI(imageUri)
            // 여기에서 선택한 이미지를 서버에 업로드하거나 다른 작업을 수행할 수 있습니다.
        }
    }
    private fun getBitmapFromImageView(imageView: ImageView): Bitmap? {
        return try {
            val drawable = imageView.drawable
            val bitmap: Bitmap = (drawable).toBitmap()
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    private fun encodeBitmapToBase64(bitmap: Bitmap?): String? {
        return bitmap?.let {
            val byteArrayOutputStream = ByteArrayOutputStream()
            it.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
    }
}