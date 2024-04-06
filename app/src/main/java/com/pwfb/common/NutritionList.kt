package com.pwfb.common

import com.pwfb.domain.NutritionEntity

object NutritionObject {
    const val CARBOHYDRATE = "carbohydrate"
    const val PROTEIN = "protein"
    const val FAT = "fat"
    const val WATER = "water"
    const val SODIUM = "sodium"
    const val POTASSIUM = "potassium"
    const val CREATINE = "creatine"
    const val DIETARY_FIBER = "dietaryFiber"

    var nutritionEntityLists = listOf(
        NutritionEntity(
            CARBOHYDRATE,
            "권장 탄수화물 섭취량(g)",
            ""
        ),
        NutritionEntity(
            PROTEIN,
            "권장 단백질 섭취량(g)",
            ""
        ),
        NutritionEntity(
            FAT,
            "권장 지방 섭취량(g)",
            ""
        ),
        NutritionEntity(
            WATER,
            "권장 수분 섭취량(ml)",
            ""
        ),
        NutritionEntity(
            SODIUM,
            "권장 나트륨 섭취량(mg)",
            ""
        ),
        NutritionEntity(
            POTASSIUM,
            "권장 칼륨 섭취량(mg)",
            ""
        ),
        NutritionEntity(
            CREATINE,
            "권장 크레아틴 섭취량(g)",
            ""
        ),
        NutritionEntity(
            DIETARY_FIBER,
            "권장 식이섬유 섭취량(g)",
            ""
        )
    )
}


