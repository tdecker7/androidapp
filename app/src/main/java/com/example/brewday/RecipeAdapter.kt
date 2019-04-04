package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity


class RecipesAdapter(private val recipesList: ArrayList<Recipe>) :
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>(){
    override fun getItemCount() = recipesList.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        var recipeNameView = holder.textView.findViewById<TextView>(R.id.recipe_name)
        var recipeStyle = holder.textView.findViewById<TextView>(R.id.recipe_style)
        var recipeButton = holder.textView.findViewById<Button>(R.id.view_recipe_button)
        recipeButton.text = "View Recipe"
        recipeNameView.text = recipesList[position].name
        recipeStyle.text = recipesList[position].style

        recipeButton.setOnClickListener {
            holder.viewRecipe(holder.textView.context, recipesList[position].id)
        }
    }

    class RecipesViewHolder(val textView: LinearLayout) : RecyclerView.ViewHolder(textView) {
        fun viewRecipe(context: Context, id: String)  {
            val intent = Intent(context, ViewRecipe::class.java)
            intent.putExtra("ID", id)
            startActivity(context, intent, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecipesAdapter.RecipesViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false) as LinearLayout
        // set the view's size, margins, paddings and layout parameters
        return RecipesViewHolder(textView)
    }

}
