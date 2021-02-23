package com.example.testglobus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class RecyclerAdapter(private val nameList: MutableList<String>, private val status: String): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rec_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.nameView?.text  = nameList[position]
       holder.statusView?.text  = status
    }

    override fun getItemCount(): Int = nameList.size

    class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        var nameView: TextView? = null
        var statusView: TextView? = null
        init {
            nameView = itemview.findViewById(R.id.textView2)
            statusView = itemview.findViewById(R.id.textView4)
        }
    }
}