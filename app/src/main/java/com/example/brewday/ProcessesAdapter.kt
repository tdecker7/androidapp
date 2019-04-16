package com.example.brewday

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_add_recipe.view.*

class ProcessesAdapter(private val processesList: ArrayList<BrewProcess>) :
    RecyclerView.Adapter<ProcessesAdapter.ProcessesViewHolder>() {

    override fun onBindViewHolder(holder: ProcessesViewHolder, position: Int) {
        var processNameView = holder.textView.findViewById<TextView>(R.id.process_name)
        var processButton = holder.textView.findViewById<Button>(R.id.view_process_button)
        processButton.text = R.string.view_process.toString()
        processNameView.text = processesList[position].name

        processButton.setOnClickListener {
            holder.viewProcess(holder.textView.context, processesList[position].id)
        }
    }

    class ProcessesViewHolder(val textView: LinearLayout) : RecyclerView.ViewHolder(textView) {
        fun viewProcess(context: Context, id: String) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ProcessesAdapter.ProcessesViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.process_item, parent, false) as LinearLayout
        return ProcessesViewHolder(textView)
    }
}
