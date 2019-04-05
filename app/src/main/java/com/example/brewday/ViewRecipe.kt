package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.brewday.DBOpenHelper

class ViewRecipe: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recipe)

        val contextRecipeId = getIntent().getStringExtra("ID")
        setupContextRecipe(contextRecipeId)
val updateRecipeButton = findViewById<Button>(R.id.update_recipe_button)
        val deleteRecipeButton = findViewById<Button>(R.id.delete_recipe_button)

        updateRecipeButton.setOnClickListener {
            val dbHandler = DBOpenHelper(this, null)
            val recipe = Recipe(
                name=findViewById<EditText>(R.id.view_recipe_name).text.toString(),
                style=findViewById<EditText>(R.id.view_recipe_style).text.toString(),
                malt=findViewById<EditText>(R.id.view_recipe_malt).text.toString(),
                hops=findViewById<EditText>(R.id.view_recipe_hops).text.toString(),
                yeast=findViewById<EditText>(R.id.view_recipe_yeast).text.toString()
            )

            dbHandler.updateRecipe(recipe)

            startMainActivity()
        }

        deleteRecipeButton.setOnClickListener {
            val dbHandler = DBOpenHelper(this, null)
            dbHandler.deleteRecipe(contextRecipeId)

            startMainActivity()
        }
    }

    private fun setupContextRecipe(contextRecipeId: String) {
        val dbHandler = DBOpenHelper(this, null)
        val contextRecipe =  dbHandler.getRecipeById(contextRecipeId)

        contextRecipe?.let {
            var recipeName = findViewById<EditText>(R.id.view_recipe_name)
            var recipeStyle = findViewById<EditText>(R.id.view_recipe_style)
            var recipeMalt = findViewById<EditText>(R.id.view_recipe_malt)
            var recipeHops = findViewById<EditText>(R.id.view_recipe_hops)
            var recipeYeast = findViewById<EditText>(R.id.view_recipe_yeast)

            recipeName.setText(contextRecipe.name)
            recipeStyle.setText(contextRecipe.style)
            recipeMalt.setText(contextRecipe.malt)
            recipeHops.setText(contextRecipe.hops)
            recipeYeast.setText(contextRecipe.yeast)
        } ?: startMainActivity()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
