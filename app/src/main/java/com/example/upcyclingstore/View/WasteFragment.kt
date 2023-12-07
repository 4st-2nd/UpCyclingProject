package com.example.upcyclingstore.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.upcyclingstore.Controller.ReceiveProductData
import com.example.upcyclingstore.Controller.RecyclerAdapter
import com.example.upcyclingstore.databinding.FragmentWasteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 * Use the [WasteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

interface WasteCallback {
    fun onFunctionCall(data: List<RecyclerAdapter.MyItem>)
}
class WasteFragment : Fragment(),WasteCallback {
    override fun onFunctionCall(data: List<RecyclerAdapter.MyItem>) {
        val adapter = RecyclerAdapter(data)
        binding.recycler.adapter = adapter
    }
    private lateinit var binding:FragmentWasteBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWasteBinding.inflate(inflater, container, false)
        val view = binding.root

        //Grid 설정
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recycler.layoutManager = layoutManager


        val jsonData = JSONObject()
        val search = binding.searchView.getQuery().toString()
        Log.d("tag",search)
        //페이지에 다시 돌아와도 검색창에 텍스트가 있나 없나
        if(search == "")
            jsonData.put("query", "SELECT * FROM Waste")
        else
            jsonData.put("query", "SELECT * FROM Waste WHERE title LIKE('%$search%');")
        ReceiveProductData.receive(jsonData,requireContext(),this)
        initSearchView(this)
        return view
    }
    private fun initSearchView(callback: WasteCallback)
    {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val jsonData = JSONObject()
                if(query == "")
                    jsonData.put("query", "SELECT * FROM Waste")
                else
                    jsonData.put("query", "SELECT * FROM Waste WHERE title LIKE('%$query%');")

                ReceiveProductData.receive(jsonData,requireContext(),callback)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.equals("")){
                    this.onQueryTextSubmit("");
                }
                return true
            }
        })
    }


}