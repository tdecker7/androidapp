package com.example.brewday

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.DatabaseUtils.dumpCursorToString
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

        val create_processes_table = ("""
            create table $processes_table_name (
            $processes_id UUID PRIMARY KEY,
            $processes_name_column TEXT,
            $processes_type_column TEXT
            )
        """)
        db.execSQL(CREATE_RECIPES_TABLE)
        db.execSQL(create_processes_table)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + processes_table_name)
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

    fun getRecipeById(id: String) : Recipe? {
        val db = this.readableDatabase

        var cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE _id = '$id'", null)

        Log.d("DATABASEINFO", "SELECT * from $TABLE_NAME where _id = '$id'")
        if(cursor.moveToFirst()) {

            Log.d("CURSORINFO", DatabaseUtils.dumpCursorToString(cursor))
            return Recipe(
                cursor.getString(cursor.getColumnIndex("$COLUMN_ID")),
                cursor.getString(cursor.getColumnIndex("recipe_name")),
                cursor.getString(cursor.getColumnIndex("recipe_style")),
                cursor.getString(cursor.getColumnIndex("recipe_malt")),
                cursor.getString(cursor.getColumnIndex("recipe_hops")),
                cursor.getString(cursor.getColumnIndex("recipe_yeast"))
            )
        }
        return null
    }

    fun getAllRecipes(): ArrayList<Recipe> {
        val db = this.readableDatabase
        val recipesList = ArrayList<Recipe>()
        Log.d("RECIPESINFO", recipesList.toString())
        var cursor =  db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        Log.d("CURSOR", dumpCursorToString(cursor))
        if (cursor.moveToFirst()) {
            do {
                val retrievedRecipe = Recipe(
                    cursor.getString(cursor.getColumnIndex("$COLUMN_ID")),
                    cursor.getString(cursor.getColumnIndex("recipe_name")),
                    cursor.getString(cursor.getColumnIndex("recipe_style")),
                    cursor.getString(cursor.getColumnIndex("recipe_malt")),
                    cursor.getString(cursor.getColumnIndex("recipe_hops")),
                    cursor.getString(cursor.getColumnIndex("recipe_yeast"))
                )

                Log.d("RECIPESINFO", retrievedRecipe.toString())
                retrievedRecipe.addRecipeToList(recipesList, retrievedRecipe)

            } while (cursor.moveToNext())
        }
        return recipesList
    }

    fun deleteRecipe(id: String) {
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id))
        db.close()
    }

    fun updateRecipe(recipe: Map<String, String>, contextId: String) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(recipe_name_column, recipe["name"])
            put(recipe_style_column, recipe["style"])
            put(recipe_malt_column, recipe["malt"])
            put(recipe_hops_column, recipe["hops"])
            put(recipe_yeast_column, recipe["yeast"])
        }

        Log.d("Values", values.toString())

        db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(contextId))
        db.close()
    }

    fun getAllProcesses(): ArrayList<BrewProcess> {
        val db = this.readableDatabase

        val processesList = ArrayList<BrewProcess>()

        val cursor = db.rawQuery("select * from $processes_table_name", null)

        if (cursor.moveToFirst()) {
            do {
                val retrievedProcess = BrewProcess(
                    cursor.getString(cursor.getColumnIndex(processes_id)),
                    cursor.getString(cursor.getColumnIndex(processes_name_column)),
                    cursor.getString(cursor.getColumnIndex(processes_type_column))
                )

                retrievedProcess.addProcessToList(processesList, retrievedProcess)
            } while (cursor.moveToNext())
        }
        return processesList
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

        val processes_table_name = "process"
        val processes_name_column = "process_name"
        val processes_type_column = "process_type"
        val processes_id = "_id"
    }
}
