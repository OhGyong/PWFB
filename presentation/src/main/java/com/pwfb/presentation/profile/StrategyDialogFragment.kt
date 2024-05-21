package com.pwfb.presentation.profile

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pwfb.presentation.R
import com.pwfb.presentation.databinding.DialogStrategyBinding


class StrategyDialogFragment: BottomSheetDialogFragment() {
    private lateinit var binding: DialogStrategyBinding

    // yes=true, no=false
    private var isFull: Boolean? = null
    private var isDry: Boolean? = null
    private var isDehydration: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogStrategyBinding.inflate(layoutInflater)
        setOnClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutParams = view.layoutParams
        layoutParams?.height = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            getWindowHeight(),
            resources.displayMetrics
        ).toInt()
        binding.clStrategy.layoutParams = layoutParams
    }

    private fun setOnClickListener() {
        binding.clChooseFull.setOnClickListener {
            binding.clChoose.visibility = View.GONE
            binding.clAmIFull.visibility = View.VISIBLE
        }

        binding.clChooseDry.setOnClickListener {
            binding.clChoose.visibility = View.GONE
            binding.clAmIDry.visibility = View.VISIBLE
        }

        binding.tvFullYes.setOnClickListener {
            isFull = true
            binding.clAmIFull.visibility = View.GONE
            if(isDry == true) {
                binding.tvResult.text = getString(R.string.stabilize_condition)
            } else {
                binding.clAmIDry.visibility = View.VISIBLE
            }
        }

        binding.tvFullNo.setOnClickListener {
            isFull = false
            binding.clAmIFull.visibility = View.GONE
            if(isDry == true) {
                binding.tvResult.text = getString(R.string.stabilize_condition)
            } else {
                binding.clAmIDry.visibility = View.VISIBLE
            }
        }

        binding.tvDryYes.setOnClickListener {
            isDry = true
            binding.clAmIDry.visibility = View.GONE
            if(isFull != null && isDehydration == null) {
                binding.tvResult.text = getString(R.string.stabilize_condition)
            }else {
                binding.clAmIFull.visibility = View.VISIBLE
            }
        }

        binding.tvDryNo.setOnClickListener {
            isDry = false
            binding.clAmIDry.visibility = View.GONE
            binding.clContinueDehydration.visibility = View.VISIBLE
        }

        binding.tvFlatYes.setOnClickListener {
            binding.clAmIFlat.visibility = View.GONE
            binding.tvResult.text = getString(R.string.consider_flat)
        }

        binding.tvFlatNo.setOnClickListener {
            binding.clAmIFlat.visibility = View.GONE
            binding.tvResult.text = getString(R.string.stabilize_condition)
        }

        binding.tvContinue.setOnClickListener {
            isDehydration = true
            binding.clContinueDehydration.visibility = View.GONE
            binding.clAmIDry.visibility = View.VISIBLE
        }
    }

    private fun getWindowHeight(): Float {
        val display = requireContext().resources?.displayMetrics
        val deviceHeight = display?.density?.let { display.heightPixels.div(it) }
        return deviceHeight!!*0.5f
    }
}