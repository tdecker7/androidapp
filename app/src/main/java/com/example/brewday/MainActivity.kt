package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brewday.DBOpenHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var recipesAdapter: RecipesAdapter
//    private lateinit var viewManager: RecyclerView.LayoutManager
//    lateinit var recipesList: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        view_recipes_main.setOnClickListener {
            viewRecipes()
        }


//        val dbHelper = DBOpenHelper(this, null)
//
//        recipesList = dbHelper.getAllRecipes()
//
//        viewManager = LinearLayoutManager(this)
//        recipesAdapter = RecipesAdapter(recipesList)
//
//        recyclerView = findViewById<RecyclerView>(R.id.rvRecipes).apply {
//            setHasFixedSize(true)
//            layoutManager = viewManager
//            adapter = recipesAdapter
//        }


//        fab.setOnClickListener {
//            addRecipe()
//        }


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    fun viewRecipes() {
        val intent = Intent(this, ViewRecipes::class.java)
        startActivity(intent)
    }

//    fun addRecipe() {
//        val intent = Intent(this, AddRecipe::class.java)
//        startActivity(intent)
//    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_recipes-> {
                val mainActivityIntent = Intent(this, MainActivity::class.java)
                startActivity(mainActivityIntent)
            }
            R.id.nav_process-> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
