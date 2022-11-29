package com.moviechacha.presentation.view.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.moviechacha.presentation.databinding.DialogPlotBinding

class PlotDialog : DialogFragment() {
    private lateinit var binding: DialogPlotBinding
    private var plot = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (arguments?.get(KEY_PLOT) as String).let {
            plot = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogPlotBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initView() {
        dialog?.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
        binding.apply {
            ivDialogBack.setOnClickListener {
                dismiss()
            }
            moviePlot = plot
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

    companion object {
        fun newInstance(plot: String): PlotDialog {
            return PlotDialog().apply {
                arguments = bundleOf(KEY_PLOT to plot)
            }
        }

        const val KEY_PLOT: String = "key_plot"
    }
}