package com.example.creddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.creddemo.CategoryDetails
import com.example.creddemo.R
import com.example.creddemo.model.CategoryItem
import com.example.creddemo.model.LayoutType

class BillsCategoryAdapter(private val context : Context,
                           private val layoutManager: GridLayoutManager? = null,
                           private val details: CategoryDetails) : RecyclerView.Adapter<BillsCategoryAdapter.CustomViewHolder>() {

    private var categoryList = emptyList<CategoryItem>()

    private val imagesList = arrayListOf<String>(
        "https://img.icons8.com/pastel-glyph/64/12B886/stack-of-money--v2.png",
        "https://img.icons8.com/glyph-neue/64/12B886/sell-property.png",
        "https://img.icons8.com/ios/50/12B886/book-and-pencil.png")

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        return when (viewType) {
            LayoutType.LIST_LAYOUT.ordinal ->
                CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_list_item, parent, false))
            else -> CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_grid_item, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) LayoutType.LIST_LAYOUT.ordinal
        else LayoutType.GRID_LAYOUT.ordinal
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val displayData = categoryList[position].displayData

        if(getItemViewType(position) == LayoutType.LIST_LAYOUT.ordinal){
            holder.itemView.findViewById<TextView>(R.id.description_list_tv).text = displayData?.description
            holder.itemView.findViewById<TextView>(R.id.title_list_tv).text = displayData?.name
            val imageView  = holder.itemView.findViewById<ImageView>(R.id.image_list_iv)
            Glide.with(context).load(imagesList[position]).into(imageView)
        }else{
            holder.itemView.findViewById<TextView>(R.id.title_grid_tv).text = displayData?.name

            val imageView  = holder.itemView.findViewById<ImageView>(R.id.image_grid_iv)
            Glide.with(context).load(imagesList[position]).into(imageView)
        }

        holder.itemView.setOnClickListener {
            details.getDetails(displayData?.name, displayData?.description)
        }
    }

    fun setData(newList: List<CategoryItem>){
        categoryList = newList
        notifyDataSetChanged()
    }

}