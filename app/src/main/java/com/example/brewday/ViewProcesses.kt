package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brewday.BrewProcess
import com.example.brewday.ProcessesAdapter
import com.example.brewday.ProcessesDBOpenerHelper

class ViewProcesses : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var processesAdapter: ProcessesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var processesList: ArrayList<BrewProcess>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setupView()
    }

    override fun onStart() {
        super.onStart()
        this.setupView()
    }

    private fun setupView() {
        setContentView(R.layout.activity_view_processes)

        val dbHelper = ProcessesDBOpenerHelper(this, null)

        processesList = dbHelper.getAllProcesses()
        viewManager = LinearLayoutManager(this)
        processesAdapter = ProcessesAdapter(processesList)
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
