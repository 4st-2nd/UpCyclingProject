package com.example.upcyclingstore.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upcyclingstore.Controller.WasteTradeAdapter
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.FragmentWasteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class WasteFragment : Fragment(), WasteTradeAdapter.OnBuyClickListener {
    private lateinit var binding: FragmentWasteBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWasteBinding.inflate(inflater, container, false)
        val view = binding.root

        val item: ArrayList<WasteTradeAdapter.WasteList> = ArrayList()
        item.add(WasteTradeAdapter.WasteList(R.drawable.plastic, "item 1"))
        item.add(WasteTradeAdapter.WasteList(R.drawable.plastic, "item 2"))
        item.add(WasteTradeAdapter.WasteList(R.drawable.plastic, "item 3"))
        item.add(WasteTradeAdapter.WasteList(R.drawable.plastic, "item 4"))
        item.add(WasteTradeAdapter.WasteList(R.drawable.plastic, "item 5"))
        item.add(WasteTradeAdapter.WasteList(R.drawable.plastic, "item 6"))
        item.add(WasteTradeAdapter.WasteList(R.drawable.plastic, "item 7"))
        item.add(WasteTradeAdapter.WasteList(R.drawable.plastic, "item 8"))

        val recyclerView: RecyclerView = binding.wasteRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = WasteTradeAdapter(item, this)

        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)

        return view
    }

    override fun onBuyClick(data: WasteTradeAdapter.WasteList) {
        // 클릭된 아이템에 대한 BottomSheetDialog를 표시
        showBottomSheetDialog(this, data)
    }

    companion object {
        fun showBottomSheetDialog(fragment: WasteFragment, data: WasteTradeAdapter.WasteList) {
            val bottomSheetView =
                LayoutInflater.from(fragment.requireContext()).inflate(R.layout.waste_bottomsheet, null)
            fragment.bottomSheetDialog.setContentView(bottomSheetView)
            fragment.bottomSheetDialog.show()

            bottomSheetView.findViewById<ImageView>(R.id.btn_close).setOnClickListener {
                fragment.bottomSheetDialog.dismiss()
            }
        }
    }
}
