package com.pwfb.common

import com.pwfb.data.Nutrition

object NutritionObject {
    const val CARBOHYDRATE = "carbohydrate"
    const val PROTEIN = "protein"
    const val FAT = "fat"
    const val WATER = "water"
    const val SODIUM = "sodium"
    const val POTASSIUM = "potassium"
    const val CREATINE = "creatine"
    const val DIETARY_FIBER = "dietaryFiber"

    var nutritionList = listOf(
        Nutrition(
            CARBOHYDRATE,
            "권장 탄수화물 섭취량(g)",
            ""
        ),
        Nutrition(
            PROTEIN,
            "권장 단백질 섭취량(g)",
            ""
        ),
        Nutrition(
            FAT,
            "권장 지방 섭취량(g)",
            ""
        ),
        Nutrition(
            WATER,
            "권장 수분 섭취량(ml)",
            ""
        ),
        Nutrition(
            SODIUM,
            "권장 나트륨 섭취량(mg)",
            ""
        ),
        Nutrition(
            POTASSIUM,
            "권장 칼륨 섭취량(mg)",
            ""
        ),
        Nutrition(
            CREATINE,
            "권장 크레아틴 섭취량(g)",
            ""
        ),
        Nutrition(
            DIETARY_FIBER,
            "권장 식이섬유 섭취량(g)",
            ""
        )
    )
}


