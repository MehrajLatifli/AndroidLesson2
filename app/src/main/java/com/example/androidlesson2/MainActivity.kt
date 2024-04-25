package com.example.androidlesson2

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import com.example.androidlesson2.databinding.ActivityMainBinding
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlin.random.Random
import com.google.android.material.materialswitch.MaterialSwitch
import androidx.appcompat.widget.AppCompatRadioButton



fun randomRange(from: Int? = null, until: Int? = null): Int {
    from.let {
        until.let {
            val result = Random.nextInt(from!!, until!!)
            Log.e("key", result.toString())
            return result
        }
    }
}


fun setSwitchAppearance(switch: MaterialSwitch, isChecked: Boolean, binding: ActivityMainBinding) {

    if (isChecked) {

        switch.thumbTintList = ColorStateList.valueOf(Color.parseColor("#4CAF50"))
        switch.trackTintList = ColorStateList.valueOf(Color.parseColor("#FFDD00"))
        switch.text = "Light"
        switch.trackDrawable?.setTint(Color.parseColor("#FFDD00"))

        switch.setTextColor(Color.BLACK)

        binding.radioGroup.forEach { view ->
            if (view is RadioButton) {
                view.setTextColor(Color.BLACK)

            }
        }

    } else {

        switch.thumbTintList = ColorStateList.valueOf(Color.parseColor("#F8F8F8"))
        switch.trackTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
        switch.trackDrawable?.setTint(Color.parseColor("#FFC0CB"))
        switch.text = "Dark"

        switch.setTextColor(Color.WHITE)

        binding.radioGroup.forEach { view ->
            if (view is RadioButton) {
                view.setTextColor(Color.WHITE)
            }
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


        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->

            setSwitchAppearance(binding.switch1, isChecked,binding)

            if (isChecked) {

                binding.root.setBackgroundColor(Color.parseColor("#FFFFFF"))

            } else {

                binding.root.setBackgroundColor(Color.parseColor("#333333"))
            }

        }




    }
}
