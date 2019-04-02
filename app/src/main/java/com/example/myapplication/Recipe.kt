package com.example.myapplication

class Recipe(val name: String,
             val style: String,
             var malt: String,
             var hops: String,
             var yeast: String,
             var image: Int = R.drawable.beer) {

    fun getRecipeName(): String {
        return name
    }

    fun getRecipeStyle(): String {
        return style
    }

    fun getRecipeMalt(): String {
        return malt
    }

    fun getRecipeHops(): String {
        return hops
    }

    fun getRecipeYeast(): String {
        return yeast
    }

    fun addRecipeToList(recipesList: ArrayList<Recipe>, recipe: Recipe) {
        recipesList.add(recipe);
    }

    fun createRecipesList(initialRecipe: Recipe): ArrayList<Recipe> {
        val recipesList = ArrayList<Recipe>()
        recipesList.add(initialRecipe)
        return recipesList
    }

//    companion object {
//
//        private var lastRecipeId = 0
//
//        fun createRecipeList(numRecipes: Int): ArrayList<Recipe> {
//            val recipes = ArrayList<Recipe>()
//
//            recipes.add(Recipe("SMASH Ale",
//                "American Pale", "2lbs 2 row",
//                "4oz Columbus", "Wyeast American Ale"))
//
//            return recipes
//        }
//
//    }
}