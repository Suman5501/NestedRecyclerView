package com.example.creddemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.creddemo.adapter.BillsCategoryAdapter
import com.example.creddemo.adapter.viewmodel.MainViewModel
import com.example.creddemo.adapter.MoneyCategoryAdapter
import com.example.creddemo.adapter.BenefitsCategoryAdapter
import com.example.creddemo.adapter.viewmodel.ViewModelFactory
import com.example.creddemo.repository.CategoryRepository

class CategoryActivity : AppCompatActivity(), CategoryDetails {

    private lateinit var viewModel: MainViewModel

    private var billsLayoutManager: GridLayoutManager? = null
    private var moneyLayoutManager: GridLayoutManager? = null
    private var benefitsLayoutManager: GridLayoutManager? = null

    private val billAdapter by lazy { BillsCategoryAdapter(this, billsLayoutManager, this) }
    private val moneyAdapter by lazy { MoneyCategoryAdapter(this, moneyLayoutManager, this) }
    private val benefitsAdapter by lazy { BenefitsCategoryAdapter(this, benefitsLayoutManager, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        setup()

        val toggle : SwitchCompat = findViewById(R.id.switch_btn)
        toggle.setOnCheckedChangeListener {_, isChecked ->
            switchLayout(isChecked)
        }

        val repository = CategoryRepository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        val response = viewModel.getCategories()


        viewModel.response.observe(this, Observer { response ->
            if(response.isSuccessful){
                val money = response.body()?.data?.get(0)?.sectionProperties?.items
                money.let { it?.let { it1 -> moneyAdapter.setData(it1) } }

                val bills = response.body()?.data?.get(1)?.sectionProperties?.items
                bills.let { it?.let { it1 -> billAdapter.setData(it1) } }

                val benefits = response.body()?.data?.get(2)?.sectionProperties?.items
                benefits.let { it?.let { it1 -> benefitsAdapter.setData(it1) } }
            }else{
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun switchLayout(checked: Boolean) {
        if(checked) {
            billsLayoutManager?.spanCount = 4
            moneyLayoutManager?.spanCount = 4
            benefitsLayoutManager?.spanCount = 4
        }else{
            billsLayoutManager?.spanCount = 1
            moneyLayoutManager?.spanCount = 1
            benefitsLayoutManager?.spanCount = 1
        }
        billAdapter.notifyItemRangeChanged(0, billAdapter.itemCount)
        moneyAdapter.notifyItemRangeChanged(0, moneyAdapter.itemCount)
        benefitsAdapter.notifyItemRangeChanged(0, benefitsAdapter.itemCount)

    }

    private fun setup() {
        val recyclerView1: RecyclerView = findViewById(R.id.parent_rv1)
        val recyclerView2: RecyclerView = findViewById(R.id.parent_rv2)
        val recyclerView3: RecyclerView = findViewById(R.id.parent_rv3)

        moneyLayoutManager = GridLayoutManager(this, 1)
        billsLayoutManager = GridLayoutManager(this, 1)
        benefitsLayoutManager = GridLayoutManager(this, 1)

        recyclerView1.adapter = moneyAdapter
        recyclerView1.layoutManager = moneyLayoutManager

        recyclerView2.adapter = benefitsAdapter
        recyclerView2.layoutManager = billsLayoutManager

        recyclerView3.adapter = billAdapter
        recyclerView3.layoutManager = benefitsLayoutManager

    }

    override fun getDetails(title: String?, description: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("nameKey", title)
        intent.putExtra("descriptionKey", description)
        startActivity(intent)
    }
}