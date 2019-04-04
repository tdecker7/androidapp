package com.example.myapplication

import java.util.*


class Recipe(var id: String = UUID.randomUUID().toString(),
             val name: String,
             val style: String,
             var malt: String,
             var hops: String,
             var yeast: String,
             var image: Int = R.drawable.beer) {

    fun addRecipeToList(recipesList: ArrayList<Recipe>, recipe: Recipe) {
        recipesList.add(recipe);
    }
}