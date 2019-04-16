package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.brewday.BrewProcess
import com.example.brewday.ProcessesDBOpenerHelper
import kotlinx.android.synthetic.main.activity_add_process.*

class AddProcess : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_process)

        val submitRecipeButton = findViewById<Button>(R.id.submit_process_button)

        ArrayAdapter.createFromResource(
            this,
            R.array.processTypeArray,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            add_process_type.adapter = adapter
        }
        submitRecipeButton.setOnClickListener { view ->
            val dbHandler = ProcessesDBOpenerHelper(this, null)

            val process = BrewProcess(
                name=findViewById<EditText>(R.id.add_process_name).text.toString(),
                type=findViewById<Spinner>(R.id.add_process_type).selectedItem.toString()
            )

            process.toString()

            dbHandler.addProcess(process)
            finish()
        }
    }


}
