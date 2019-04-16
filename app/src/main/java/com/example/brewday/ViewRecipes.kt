package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brewday.DBOpenHelper

class ViewRecipes : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var recipesList: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setupView()
    }

    override fun onStart() {
        super.onStart()
        this.setupView()
    }

    private fun setupView() {
        setContentView(R.layout.activity_view_recipes)

        val dbHelper = DBOpenHelper(this, null)

        recipesList = dbHelper.getAllRecipes()
        viewManager = LinearLayoutManager(this)
        recipesAdapter = RecipesAdapter(recipesList)
        recyclerView = findViewById<RecyclerView>(R.id.rvRecipes).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = recipesAdapter
        }

    }
    fun addRecipe(view: View) {
        val addRecipeIntent = Intent(this, AddRecipe::class.java)
        startActivity(addRecipeIntent)
    }
}
