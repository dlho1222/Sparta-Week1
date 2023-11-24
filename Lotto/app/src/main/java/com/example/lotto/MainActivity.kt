package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.lotto.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val numTextViewList: List<TextView> by lazy {
        listOf<TextView>(
            binding.tvNum1,
            binding.tvNum2,
            binding.tvNum3,
            binding.tvNum4,
            binding.tvNum5,
            binding.tvNum6,
        )
    }
    private var didRun = false
    private val pickNumberSet = hashSetOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.numberPicker.apply {
            maxValue = 45
            minValue = 1
            value = 25
        }
        binding.btnAddNumber.setOnClickListener {
            addNumber()
        }
        binding.btnInit.setOnClickListener {
            clear()
        }
        binding.btnRun.setOnClickListener {
            addRun()
        }
    }

    private fun addNumber() {
        when {
            didRun -> showToast("초기화 후에 시도해주세요")
            pickNumberSet.size >= 5 -> showToast("숫자는 최대 5개까지 선택할 수 있습니다.")
            pickNumberSet.contains(binding.numberPicker.value) -> showToast("이미 선택된 숫자입니다.")
            else -> {
                val textView = numTextViewList[pickNumberSet.size]
                textView.isVisible = true
                textView.text = binding.numberPicker.value.toString()
                setNumberBack(binding.numberPicker.value, textView)
                pickNumberSet.add(binding.numberPicker.value)
            }
        }
    }

    private fun showToast(message: String) {
        return Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setNumberBack(number: Int, textView: TextView) {
        val background = when (number) {
            in 1..10 -> R.drawable.circle_yellow
            in 11..20 -> R.drawable.circle_blue
            in 21..30 -> R.drawable.circle_red
            in 31..40 -> R.drawable.circle_gray
            else -> R.drawable.circle_green
        }
        textView.background = ContextCompat.getDrawable(this, background)
    }

    private fun clear() {
        pickNumberSet.clear()
        numTextViewList.forEach { it.isVisible = false }
        didRun = false
        binding.numberPicker.value = 25
    }

    private fun addRun() {
        val list = getRandom()
        didRun = true
        list.forEachIndexed { index, number ->
            val textView = numTextViewList[index]
            textView.text = number.toString()
            textView.isVisible = true
            setNumberBack(number, textView)
        }
    }

    private fun getRandom(): List<Int> {
        val numbers = (1..45).filter { it !in pickNumberSet }
        return (pickNumberSet + numbers.shuffled().take(6 - pickNumberSet.size)).sorted()
    }
}