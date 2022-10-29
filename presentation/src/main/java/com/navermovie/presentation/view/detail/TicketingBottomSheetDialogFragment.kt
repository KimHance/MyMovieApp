package com.navermovie.presentation.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.navermovie.presentation.*
import com.navermovie.presentation.databinding.FragmentTicketingBottomSheetBinding

class TicketingBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentTicketingBottomSheetBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_ticketing_bottom_sheet,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.run {
            ivDown.setOnClickListener {
                dismiss()
            }
            llCgv.setOnClickListener {
                doTicketing(CGV_TICKETING)
            }
            llLotte.setOnClickListener {
                doTicketing(LOTTE_CINEMA_TICKETING)
            }
            llMegaBox.setOnClickListener {
                doTicketing(MEGA_BOX_BOOKING)
            }
        }
    }

    private fun doTicketing(link: String) {
        requireActivity().startActivity(
            Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse(link))
                .setPackage(CHROME)
        )
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}