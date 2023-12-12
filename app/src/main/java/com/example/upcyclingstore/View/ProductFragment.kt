package com.example.upcyclingstore.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.upcyclingstore.Controller.ProductRecyclerAdapter
import com.example.upcyclingstore.Controller.ReceiveProductData
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.FragmentProductBinding
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 * Use the [WasteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

interface ProductCallback {
    fun onFunctionCall(data: List<ProductRecyclerAdapter.MyItem>)
}
class ProductFragment : Fragment(),ProductCallback {
    var search: String = ""
    override fun onFunctionCall(data: List<ProductRecyclerAdapter.MyItem>) {
        val adapter = ProductRecyclerAdapter(data)
        binding.recycler.adapter = adapter
    }

    private lateinit var binding: FragmentProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        val view = binding.root

        //Grid 설정
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recycler.layoutManager = layoutManager

        //등록 버튼 리스너
        binding.btnProductReg.setOnClickListener {
            val newFragment = WriteProductFragment()
        // 프래그먼트 트랜잭션 시작
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
        // 트랜잭션에 새로운 프래그먼트 추가
            transaction.replace(R.id.flFragment, newFragment)
        // 백 스택에 추가하여 사용자가 뒤로가기 버튼을 눌렀을 때 이전 프래그먼트로 돌아갈 수 있도록 함
            transaction.addToBackStack("product")
        // 트랜잭션 커밋
            transaction.commit()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        initSearchView(this)
        val jsonData = JSONObject()
        //페이지에 다시 돌아와도 검색창에 텍스트가 있나 없나
        jsonData.put("query", "SELECT Product.*, User.name FROM Product JOIN User ON Product.userID = User.userID WHERE title LIKE('%$search%') AND amount > 0;")
        ReceiveProductData.receive(jsonData, requireContext(), this)
    }

    private fun initSearchView(callback: ProductCallback) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val jsonData = JSONObject()
                jsonData.put("query", "SELECT Product.*, User.name FROM Product JOIN User ON Product.userID = User.userID WHERE title LIKE('%$query%') AND amount > 0;")

                ReceiveProductData.receive(jsonData, requireContext(), callback)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.equals("")) {
                    this.onQueryTextSubmit("");
                }
                if (newText != null) {
                    search = newText
                }
                return true
            }
        })
    }
}