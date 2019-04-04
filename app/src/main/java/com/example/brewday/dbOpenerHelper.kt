package com.example.brewday

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.myapplication.Recipe

class DBOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        val CREATE_RECIPES_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " UUID PRIMARY KEY," +
                recipe_name_column
                + " TEXT," +
                recipe_style_column
                + " TEXT," +
                recipe_malt_column
                + " TEXT," +
                recipe_hops_column
                + " TEXT," +
                recipe_yeast_column
                + " TEXT"
                + ")")
        db.execSQL(CREATE_RECIPES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addRecipe(recipe: Recipe) {
        val values = ContentValues()
        values.put(COLUMN_ID, recipe.id)
        values.put(recipe_name_column, recipe.name)
        values.put(recipe_style_column, recipe.style)
        values.put(recipe_malt_column, recipe.malt)
        values.put(recipe_hops_column, recipe.hops)
        values.put(recipe_yeast_column, recipe.yeast)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllRecipes(): ArrayList<Recipe> {
        val db = this.readableDatabase
        val recipesList = ArrayList<Recipe>()
        Log.d("RECIPESINFO", recipesList.toString())
        var cursor =  db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val recipe_name = cursor.getString(cursor.getColumnIndex("recipe_name"))
                val recipe_style = cursor.getString(cursor.getColumnIndex("recipe_style"))
                Log.d("DATABASEINFO", "$recipe_name, $recipe_style")
                val retrievedRecipe = Recipe(
                    cursor.getString(cursor.getColumnIndex("recipe_name")),
                    cursor.getString(cursor.getColumnIndex("recipe_style")),
                    cursor.getString(cursor.getColumnIndex("recipe_malt")),
                    cursor.getString(cursor.getColumnIndex("recipe_hops")),
                    cursor.getString(cursor.getColumnIndex("recipe_yeast"))
                )

                Log.d("RECIPESINFO", retrievedRecipe.toString())
//
                retrievedRecipe.addRecipeToList(recipesList, retrievedRecipe)

            } while (cursor.moveToNext())
        }
        return recipesList
    }

    fun deleteRecipe(id: Int) {
        val db = this.readableDatabase

        db.rawQuery("DELETE FROM $TABLE_NAME where id = $id", null)
    }

    fun updateRecipe(recipe: Recipe) {
        val db = this.readableDatabase

        db.rawQuery(
            "UPDATE $TABLE_NAME " +
                    "SET $recipe_name_column = ${recipe.name} " +
                    "$recipe_style_column = ${recipe.style} " +
                    "$recipe_malt_column = ${recipe.malt} " +
                    "$recipe_hops_column = ${recipe.hops} " +
                    "$recipe_yeast_column = ${recipe.yeast} " +
                    "where id = ${recipe.id};",
        null)
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "brewday.db"
        val TABLE_NAME = "recipes"
        val COLUMN_ID = "_id"
        val recipe_name_column = "recipe_name"
        val recipe_style_column = "recipe_style"
        val recipe_malt_column = "recipe_malt"
        val recipe_hops_column = "recipe_hops"
        val recipe_yeast_column = "recipe_yeast"
    }
}
