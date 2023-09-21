package com.example.creddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.creddemo.CategoryDetails
import com.example.creddemo.R
import com.example.creddemo.model.CategoryItem

class CategoryAdapter(private val context: Context, private val details: CategoryDetails)
    : RecyclerView.Adapter<CategoryAdapter.CustomViewHolder>() {

    private var categoryList = emptyList<CategoryItem?>()

    companion object {
        const val SPAN_ONE = 1
        const val SPAN_FOUR = 4
        const val VIEW_TYPE_LIST = 1
        const val VIEW_TYPE_GRID = 2
    }

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title : TextView = itemView.findViewById(R.id.title_list_tv)
        val description : TextView = itemView.findViewById(R.id.description_list_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_list_item, parent, false))
    }


    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val displayData = categoryList[position]?.displayData

        holder.title.text = displayData?.name
        holder.description.text = displayData?.description

        holder.itemView.setOnClickListener {
            details.getDetails(displayData?.name, displayData?.description)
        }
    }

    fun setData(newList: List<CategoryItem>){
        categoryList = newList
        notifyDataSetChanged()
    }

}