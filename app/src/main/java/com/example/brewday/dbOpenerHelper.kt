package com.example.brewday

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.Recipe

class MindOrksDBOpenHelper(context: Context,
                           factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                recipe_name_column
                + " TEXT" +
                recipe_style_column
                + " TEXT" +
                recipe_malt_column
                + " TEXT" +
                recipe_hops_column
                + " TEXT" +
                recipe_yeast_column
                + " TEXT"
                + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun addRecipe(recipe: Recipe) {
        val values = ContentValues()
        values.put(recipe_name_column, recipe.name)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getAllRecipes(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
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
