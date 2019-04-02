package com.example.myapplication

import com.example.myapplication.R
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.nav_header_main.view.*


class RecipesAdapter(private val recipesList: ArrayList<Recipe>) :
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>(){
    override fun getItemCount() = recipesList.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val position = 0
        var recipeNameView = holder.textView.findViewById<TextView>(R.id.recipe_name)
        var recipeStyle = holder.textView.findViewById<TextView>(R.id.recipe_style)
        var recipeButton = holder.textView.findViewById<Button>(R.id.view_recipe_button)
        var recipeImage = holder.textView.findViewById<ImageView>(R.id.recipe_image)
        recipeButton.text = "View Recipe"
        recipeNameView.text = recipesList[position].name
        recipeStyle.text = recipesList[position].style
        recipeImage.setImageResource(recipesList[position].image)
//        holder.textView.text = recipesList[position].name
    }

    class RecipesViewHolder(val textView: LinearLayout) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecipesAdapter.RecipesViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false) as LinearLayout
        // set the view's size, margins, paddings and layout parameters
        return RecipesViewHolder(textView)
    }

//    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
//        // - get element from your dataset at this position
//        // - replace the contents of the view with that element
//        holder.textView.text = recipesList[position]
//    }
}

//class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val context = parent.context
//        val inflater = LayoutInflater.from(context)
//
//        // Inflate the custom layout
//        val recipeView = inflater.inflate(R.layout.recipe_item, parent, false)
//
//        // Return a new holder instance
//        return ViewHolder(recipeView)
//
//    }
//
//    override fun getItemCount(): Int {
//        return RecipesList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        // Get the data model based on position
//        val recipe = RecipesList.get(position)
//
//        // Set item views based on your views and data model
//        val textView = holder.nameTextView
//        textView.setText(recipe.getRecipeName())
//        val button = holder.viewButton
////        button.setText(if (recipe.isOnline()) "Message" else "Offline")
////        button.setEnabled(contact.isOnline())
//    }
//
//    // Provide a direct reference to each of the views within a data item
//    // Used to cache the views within the item layout for fast access
//    inner class ViewHolder
//    // We also create a constructor that accepts the entire item row
//    // and does the view lookups to find each subview
//        (itemView: View) : RecyclerView.ViewHolder(itemView) {
//        // Your holder should contain a member variable
//        // for any view that will be set as you render a row
//        var nameTextView: TextView
//        var viewButton: Button
//
//        init {
//
//            nameTextView = itemView.findViewById(R.id.recipe_name)
//            viewButton = itemView.findViewById(R.id.view_recipe_button) as Button
//        }// Stores the itemView in a public final member variable that can be used
//        // to access the context from any ViewHolder instance.
//    }
//
//    var recipe = Recipe("SMASH", "American Pale", "2lbs 2-row", "4oz Columbus", "Wyeast American")
//    private var RecipesList: List<Recipe> = recipe.createRecipesList(recipe)
//
//    // Pass in the contact array into the constructor
//    fun RecipesAdapter(recipes: List<Recipe>): List<Recipe> {
//        RecipesList = recipes
//        return RecipesList
//    }
//}