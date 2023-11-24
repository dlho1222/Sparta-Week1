package com.example.mbti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.mbti.databinding.ActivityMainBinding
import com.example.mbti.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    val questionnaireResults = QuestionnaireResults()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPagerAdapter = ViewPagerAdapter(this)
        binding.viewPager.apply {
            adapter = viewPagerAdapter
            isUserInputEnabled = false
        }
    }

    fun moveToNextQuestion() {
        if (binding.viewPager.currentItem == 3) {
            val intent = Intent(this,ResultActivity::class.java)
            intent.putIntegerArrayListExtra("results",ArrayList(questionnaireResults.results))
            startActivity(intent)
        } else {
            val nextItem = binding.viewPager.currentItem + 1
            if (nextItem < binding.viewPager.adapter?.itemCount ?: 0){
                binding.viewPager.setCurrentItem(nextItem,true)
            }
        }
    }


}
//response를 그룹화하고 각각의 개수의 max값을 mostFrequent에 저장 
class QuestionnaireResults {
    val results = mutableListOf<Int>()
    fun addResponses(response: List<Int>) {
        val mostFrequent = response.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
        mostFrequent?.let { results.add(it) }
    }
}