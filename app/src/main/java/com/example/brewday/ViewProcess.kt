package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.brewday.ProcessesDBOpenerHelper

class ViewProcess: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_process)

        val contextProcessId = getIntent().getStringExtra("ID")
        setupContextProcess(contextProcessId)
        val updateProcessButton = findViewById<Button>(R.id.update_process_button)
        val deleteProcessButton = findViewById<Button>(R.id.delete_process_button)

        updateProcessButton.setOnClickListener {
            val dbHandler = ProcessesDBOpenerHelper(this, null)
            val name = findViewById<EditText>(R.id.view_process_name).text.toString()
            val type = findViewById<EditText>(R.id.view_process_type).text.toString()

            val process = mapOf(
                "name" to name,
                "type" to type
            )

            dbHandler.updateProcess(process, contextProcessId)

            finish()
        }

        deleteProcessButton.setOnClickListener {
            val dbHandler = ProcessesDBOpenerHelper(this, null)
            dbHandler.deleteProcess(contextProcessId)

            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }

    private fun setupContextProcess(contextProcessId: String) {
        val dbHandler = ProcessesDBOpenerHelper(this, null)
        val contextProcess = dbHandler.getProcessById(contextProcessId)

        contextProcess?.let {
            var processName = findViewById<EditText>(R.id.view_process_name)
            var processType = findViewById<EditText>(R.id.view_process_type)

            processName.setText(contextProcess.name)
            processType.setText(contextProcess.type)
        } ?: startMainActivity()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

