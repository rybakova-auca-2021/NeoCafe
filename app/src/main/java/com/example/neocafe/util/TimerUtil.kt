package com.example.neocafe.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.neocafe.R

class TimerUtil(
    private val context: Context,
    private val button: Button,
    private val navController: NavController
) {
    private lateinit var timer: CountDownTimer
    private var remainingSeconds: Long = 0

    fun startTimer(duration: Long, interval: Long) {
        timer = object : CountDownTimer(duration, interval) {
            override fun onTick(millisUntilFinished: Long) {
                remainingSeconds = millisUntilFinished / 1000
                val buttonText = "Отправить повторно через $remainingSeconds сек."
                button.isEnabled = false
                button.text = buttonText
            }

            @SuppressLint("ResourceAsColor")
            override fun onFinish() {
                button.isEnabled = true
                val enabledColor = ContextCompat.getColor(context, R.color.main)
                button.setBackgroundColor(enabledColor)
                button.text = "Отправить повторно"
                remainingSeconds = 0
            }
        }
        timer.start()
    }

    fun cancelTimer() {
        timer.cancel()
    }
}