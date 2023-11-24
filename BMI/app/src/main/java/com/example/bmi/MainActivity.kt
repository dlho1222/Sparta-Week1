package com.example.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            if(binding.etHeight.text.isEmpty()){
                Toast.makeText(this, "신장을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(binding.etWeight.text.isEmpty()){
                Toast.makeText(this, "키를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val height = binding.etHeight.text.toString().toInt()
            val weight = binding.etWeight.text.toString().toInt()
            Intent(this,ResultActivity::class.java).apply {
                putExtra("height",height)
                putExtra("weight",weight)
                startActivity(this)
            }
        }
    }
}