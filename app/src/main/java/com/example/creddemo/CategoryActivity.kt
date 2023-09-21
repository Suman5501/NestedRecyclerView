package com.example.creddemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLogTags.Description
import android.util.Log
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.creddemo.adapter.MainViewModel
import com.example.creddemo.adapter.SectionAdapter
import com.example.creddemo.adapter.ViewModelFactory
import com.example.creddemo.repository.CategoryRepository

class CategoryActivity : AppCompatActivity(), CategoryDetails {

    private lateinit var viewModel: MainViewModel

    private val parentAdapter by lazy { SectionAdapter(this, this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        setup()


        val repository = CategoryRepository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        val response = viewModel.getCategories()

        viewModel.response.observe(this, Observer { response ->
            if(response.isSuccessful){
                response.body()?.data?.let { parentAdapter.setData(it)}
            }else{
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setup() {
        val recyclerView: RecyclerView = findViewById(R.id.parent_rv)

        recyclerView.adapter = parentAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun getDetails(title: String?, description: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("nameKey", title)
        intent.putExtra("descriptionKey", description)
        startActivity(intent)
    }
}