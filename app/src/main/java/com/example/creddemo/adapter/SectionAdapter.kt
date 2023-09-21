package com.example.creddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.creddemo.CategoryDetails
import com.example.creddemo.R
import com.example.creddemo.model.Section

class SectionAdapter(private val context : Context,
                     private val details: CategoryDetails) : RecyclerView.Adapter<SectionAdapter.CustomViewHolder>() {

    private var sectionList = emptyList<Section>()

    private val categoryAdapter by lazy { CategoryAdapter(context, details) }

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.section_tittle)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.child_rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.parent_row_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return sectionList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val sectionProperty = sectionList[position].sectionProperties
        holder.title.text = sectionProperty.title

        categoryAdapter.setData(sectionProperty.items)

        holder.recyclerView.adapter = categoryAdapter
        holder.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun setData(newList: List<Section>){
        sectionList = newList
        notifyDataSetChanged()
    }

}