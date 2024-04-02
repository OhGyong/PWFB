package com.pwfb.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pwfb.R
import com.pwfb.common.NutritionObject
import com.pwfb.domain.Nutrition
import com.pwfb.databinding.ListNutritionBinding
import javax.inject.Inject

class ListAdapter @Inject constructor(
    private val context: Context,
    private val nutritionList: List<Nutrition>,
    private val kg: Double,
    private val dDay: Int
): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ListNutritionBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(nutrition: Nutrition) {
            binding.tvExplain.text = nutrition.explain
            binding.tvIntake.text = nutrition.intake

            if(nutrition.type == NutritionObject.DIETARY_FIBER) {
                binding.vDivider.visibility = View.GONE
            }

            setDDayIntake(binding, nutrition)

        }
    }

    fun setDDayIntake(
        binding: ListNutritionBinding,
        nutrition: Nutrition
    ) {
        binding.tvIntake.text = when(nutrition.type) {
            NutritionObject.CARBOHYDRATE -> {
                when(dDay) {
                    -6, -5, -4 -> {
                        context.getString(R.string.home_carbohydrate_day_654)
                    }
                    -3 -> {
                        context.getString(R.string.home_carbohydrate_day_32, (kg*10).toInt())
                    }
                    -2 -> {
                        context.getString(R.string.home_carbohydrate_day_32, (kg*2*10/3).toInt())
                    }
                    -1 -> {
                        context.getString(R.string.home_carbohydrate_day_1, kg.toInt(), (kg*3).toInt())
                    }
                    else -> {
                        context.getString(R.string.home_carbohydrate_day_7)
                    }
                }
            }

            NutritionObject.PROTEIN -> {
                when(dDay) {
                    -6, -5, -4 -> {
                        context.getString(R.string.home_protein_day_654, (kg*2.4).toInt(), (kg*3).toInt())
                    }
                    -3, -2 -> {
                        context.getString(R.string.home_protein_day_32, (kg*1.6).toInt())
                    }
                    -1 -> {
                        context.getString(R.string.home_protein_day_1, (kg*3).toInt())
                    }
                    else -> {
                      context.getString(R.string.nutrition_usually_intake, "🥩")
                    }
                }
            }

            NutritionObject.FAT -> {
                when(dDay) {
                    -7, -6, -5, -4 -> {
                        context.getString(R.string.home_fat_day_7654, (kg*0.7).toInt(), (kg*1.2).toInt())
                    }
                    -3, -2 -> {
                        context.getString(R.string.home_fat_day_32, (kg*0.5).toInt())
                    }
                    -1 -> {
                        context.getString(R.string.home_fat_day_1, (kg*0.7).toInt(), (kg*1.0).toInt())
                    }
                    else -> {
                        context.getString(R.string.nutrition_usually_intake, "🥜")
                    }
                }
            }

            NutritionObject.WATER -> {
                when(dDay) {
                    -3, -2 -> {
                        context.getString(R.string.home_water_day_3)
                    }
                    -1 -> {
                        context.getString(R.string.home_water_day_1)
                    }
                    else -> {
                        context.getString(R.string.nutrition_usually_intake, "💧")
                    }
                }
            }

            NutritionObject.SODIUM -> {
                when(dDay) {
                    -3 -> {
                        context.getString(R.string.home_sodium_day_3)
                    }
                    -2 -> {
                        context.getString(R.string.home_sodium_day_2)
                    }
                    -1 -> {
                        context.getString(R.string.home_sodium_day_1)
                    }
                    else -> {
                        context.getString(R.string.nutrition_usually_intake, "🧂")
                    }
                }
            }

            NutritionObject.POTASSIUM -> {
                when(dDay) {
                    -3 -> {
                        context.getString(R.string.home_potassium_day_3)
                    }
                    else -> {
                        context.getString(R.string.nutrition_usually_intake, "🍌")
                    }
                }
            }

            NutritionObject.CREATINE -> {
                when(dDay) {
                    -7, -6, -5, -4 -> {
                        context.getString(R.string.home_creatine_day_7654)
                    }
                    -3, -2, -1 -> {
                        context.getString(R.string.home_creatine_day_321)
                    }
                    else -> {
                        context.getString(R.string.nutrition_usually_intake, "🔥")
                    }
                }
            }

            NutritionObject.DIETARY_FIBER -> {
                when(dDay) {
                    -6, -5, -4 -> {
                        context.getString(R.string.home_dietary_fiber_day_654)
                    }
                    -3, -2, -1 -> {
                        context.getString(R.string.home_dietary_fiber_day_321)
                    }
                    else -> {
                        context.getString(R.string.nutrition_usually_intake, "🥦")
                    }
                }
            }

            else -> { context.getString(R.string.nutrition_usually_intake) }
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