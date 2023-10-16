package com.pouyaa.model

data class Meal(
    val id: String,
    val name: String,
    val imageUrl: String,
    val category: String,
    val nationality: String,
    val source: String,
    val instructions: String,
    val ingredients: List<Ingredient>
)