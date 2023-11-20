package com.example.upcyclingstore.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.upcyclingstore.R
import com.example.upcyclingstore.databinding.FragmentWasteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * A simple [Fragment] subclass.
 * Use the [WasteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WasteFragment : Fragment() {
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

        val bottomSheetView = inflater.inflate(R.layout.waste_bottomsheet, null)
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        bottomSheetDialog.setContentView(bottomSheetView)

        binding.btnBuy.setOnClickListener {
            bottomSheetDialog.show()
        }

        bottomSheetView.findViewById<ImageView>(R.id.btn_close).setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        return view
    }

}