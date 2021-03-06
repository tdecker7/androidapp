package com.example.brewday

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ViewProcess

class ProcessesAdapter(private val processesList: ArrayList<BrewProcess>) :
    RecyclerView.Adapter<ProcessesAdapter.ProcessesViewHolder>() {
    override fun getItemCount() = processesList.size

    override fun onBindViewHolder(holder: ProcessesViewHolder, position: Int) {
        var processNameView = holder.textView.findViewById<TextView>(R.id.process_name)
        var processTypeView = holder.textView.findViewById<TextView>(R.id.process_type)
        var processButton = holder.textView.findViewById<Button>(R.id.view_process_button)
        processButton.text = "View Process"
        processNameView.text = processesList[position].name
        processTypeView.text = processesList[position].type

        processButton.setOnClickListener {
            holder.viewProcess(holder.textView.context, processesList[position].id)
        }
    }

    class ProcessesViewHolder(val textView: LinearLayout) : RecyclerView.ViewHolder(textView) {
        fun viewProcess(context: Context, id: String) {
            val viewProcessIntent = Intent(context, ViewProcess::class.java)
            viewProcessIntent.putExtra("ID", id)
            startActivity(context, viewProcessIntent, null)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ProcessesAdapter.ProcessesViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.process_item, parent, false) as LinearLayout
        return ProcessesViewHolder(textView)
    }
}
