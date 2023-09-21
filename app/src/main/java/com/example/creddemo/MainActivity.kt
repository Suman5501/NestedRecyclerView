package com.example.creddemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn : Button = findViewById(R.id.category_btn)
        val titleTv : TextView = findViewById(R.id.title_main_tv)
        val descriptionTv : TextView = findViewById(R.id.description_main_tv)

        btn.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }

        val title = intent.getStringExtra("nameKey")
        val description = intent.getStringExtra("descriptionKey")
        titleTv.text = "CRED "+ title
        descriptionTv.text = description


    }
}