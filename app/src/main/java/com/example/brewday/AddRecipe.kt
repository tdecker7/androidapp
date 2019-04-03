package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.brewday.DBOpenHelper

class AddRecipe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        val submitRecipeButton = findViewById<Button>(R.id.submit_recipe_button)

        submitRecipeButton.setOnClickListener { view ->
            val dbHandler = DBOpenHelper(this, null)
            val recipe = Recipe(
                findViewById<EditText>(R.id.add_recipe_name).text.toString(),
                findViewById<EditText>(R.id.add_recipe_style).text.toString(),
                findViewById<EditText>(R.id.add_recipe_malt).text.toString(),
                findViewById<EditText>(R.id.add_recipe_hops).text.toString(),
                findViewById<EditText>(R.id.add_recipe_yeast).text.toString()
            )

            Log.d("DATABASEINFO", recipe.toString())

            dbHandler.addRecipe(recipe)
        }
    }


}
