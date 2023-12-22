package com.pwfb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pwfb.data.Nutrition
import com.pwfb.databinding.ListNutritionBinding
import javax.inject.Inject

class ListAdapter @Inject constructor(
    private val nutritionList: List<Nutrition>
): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ListNutritionBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(nutrition: Nutrition) {
            binding.tvExplain.text = nutrition.explain
            binding.tvIntake.text = nutrition.intake
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListNutritionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return nutritionList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(nutritionList[position])
    }
}