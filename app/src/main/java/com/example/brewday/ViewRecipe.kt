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

        val updateRecipeButton = findViewById<Button>(R.id.update_recipe_button)
        val deleteRecipeButton = findViewById<Button>(R.id.delete_recipe_button)

        updateRecipeButton.setOnClickListener {
            val dbHandler = DBOpenHelper(this, null)
            val recipe = Recipe(
                findViewById<EditText>(R.id.view_recipe_name).text.toString(),
                findViewById<EditText>(R.id.view_recipe_style).text.toString(),
                findViewById<EditText>(R.id.view_recipe_malt).text.toString(),
                findViewById<EditText>(R.id.view_recipe_hops).text.toString(),
                findViewById<EditText>(R.id.view_recipe_yeast).text.toString()
            )

            dbHandler.updateRecipe(recipe)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
