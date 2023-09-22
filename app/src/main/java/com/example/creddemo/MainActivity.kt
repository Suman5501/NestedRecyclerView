package com.example.creddemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

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

        val title : String? = intent.getStringExtra("nameKey")

        titleTv.text = if (title.isNullOrEmpty()) "CRED " else "CRED " + title

        val description = intent.getStringExtra("descriptionKey")

        descriptionTv.text = description


    }
}