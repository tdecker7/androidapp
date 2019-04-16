package com.example.brewday

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ViewProcesses : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var processesAdapter: ProcessesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var processesList: ArrayList<BrewProcess>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setupView()
    }

    private fun setupView() {
        setContentView(R.layout.activity_view_processes)

        val dbHelper = DBOpenHelper(this, null)

        processesList = dbHelper.getAllProcesses()
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.rvProcesses).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = processesAdapter
        }
    }

    fun addProcess(view: View) {
        val addProcessIntent = Intent(this, AddProcess::class.java)
        startActivity(addProcessIntent)

    }
}
