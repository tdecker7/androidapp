package com.example.brewday

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProcessesDBOpenerHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
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
        db.execSQL(create_processes_table)
        db.execSQL(CREATE_RECIPES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + processes_table_name)
        onCreate(db)
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

    fun getProcessById(id: String) : BrewProcess? {
        val db = this.readableDatabase

        var cursor = db.rawQuery("SELECT * FROM $processes_table_name WHERE _id = '$id'", null)

        if(cursor.moveToFirst()) {

            return BrewProcess(
                cursor.getString(cursor.getColumnIndex(processes_id)),
                cursor.getString(cursor.getColumnIndex(processes_name_column)),
                cursor.getString(cursor.getColumnIndex(processes_type_column))
            )
        }
        return null

    }

    fun addProcess(process: BrewProcess) {
        val values = ContentValues()
        values.put(processes_id, process.id)
        values.put(processes_name_column, process.name)
        values.put(processes_type_column, process.type)

        val db = this.writableDatabase
        db.insert(processes_table_name, null, values)
        db.close()

    }

    fun updateProcess(process: Map<String, String>, contextProcessId: String?) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(processes_name_column, process["name"])
            put(processes_type_column, process["type"])
        }

        db.update(processes_table_name, values, "$processes_id = ?", arrayOf(contextProcessId))
        db.close()
    }

    fun deleteProcess(id: String) {
        val db = this.writableDatabase
        db.delete(processes_table_name, "$processes_id = ?", arrayOf(id))
        db.close()
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
