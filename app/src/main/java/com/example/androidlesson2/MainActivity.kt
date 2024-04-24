package com.example.androidlesson2

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlesson2.databinding.ActivityMainBinding
import kotlin.random.Random

fun randomRange(from: Int? = null, until: Int? = null): Int {
    from.let {
        until.let {
            val result = Random.nextInt(from!!, until!!)
            Log.e("key", result.toString())
            return result
        }
    }
}

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var randomNumber: Int? = null

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        randomNumber = randomRange(1, 6)

        var count = 3

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            count--
            Log.e("key2", count.toString())

            val selectedRadioButton = binding.root.findViewById<RadioButton>(checkedId)
            val selectedText = selectedRadioButton.text.toString()

            when (selectedText.toIntOrNull()) {
                in 1..5 -> {
                    if (count > 0) {
                        if (selectedText.contains(randomNumber.toString())) {
                            binding.textView.setTextColor(Color.parseColor("#00FF00"))
                            binding.textView.text = "Congratulation"
                            count = 3
                        } else {
                            binding.textView.setTextColor(Color.parseColor("#FFA500"))
                            binding.textView.text = "Your chance: $count"
                        }
                    }
                    if (count <= 0) {
                        binding.textView.setTextColor(Color.parseColor("#FF0000"))
                        binding.textView.text = "Try Again"
                        randomNumber = randomRange(1, 6)
                        count = 3
                    }
                }
            }
        }
    }
}
