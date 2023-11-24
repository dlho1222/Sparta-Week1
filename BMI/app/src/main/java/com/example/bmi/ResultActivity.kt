package com.example.bmi

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.bmi.databinding.ActivityResultBinding
import kotlin.math.pow
import kotlin.math.round

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        var value = weight / (height / 100.0).pow(2.0)
        value = round(value * 10) / 10 //첫째 자리까지 둘째 자리까지는  100/100
        var resultText = ""
        var resImage = 0
        var resColor = 0
        when {
            value < 18.5 -> {
                resultText = "저체중"
                resImage = R.drawable.img_lv1
                resColor = Color.YELLOW
            }

            value < 23.0 -> {
                resultText = "정상 체중"
                resImage = R.drawable.img_lv2
                resColor = Color.GREEN
            }

            value < 25.0 -> {
                resultText = "과체중"
                resImage = R.drawable.img_lv3
                resColor = Color.BLACK
            }

            value < 30.0 -> {
                resultText = "경도 비만"
                resImage = R.drawable.img_lv4
                resColor = Color.CYAN
            }

            value < 35.0 -> {
                resultText = "중정도 비만"
                resImage = R.drawable.img_lv5
                resColor = Color.MAGENTA
            }

            else -> {
                resultText = "고도 비만"
                resImage = R.drawable.img_lv5
                resColor = Color.RED
            }
        }

        binding.tvResText.apply {
            text = resultText
            setTextColor(resColor)
        }
        binding.tvResValue.text = value.toString()
        binding.ivResImage.setImageResource(resImage)
        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}