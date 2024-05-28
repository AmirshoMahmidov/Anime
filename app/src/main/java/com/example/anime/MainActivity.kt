package com.example.anime


import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.anime.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.abu)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.ot)

        binding.startButton.setOnClickListener {
            startTimer()
        }
    }

    private fun startTimer() {
        timer?.cancel()

        timer = object : CountDownTimer(21000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = ((21000 - millisUntilFinished) / 1000).toInt()
                binding.timerTextView.text = seconds.toString()

                when (seconds) {

                    5 -> {
                        binding.lottieAnimationView.visibility = View.VISIBLE
                        binding.lottieAnimationView.playAnimation()
                    }

                    0 -> {
                        if (!mediaPlayer2.isPlaying) {
                            mediaPlayer2.start()
                        }
                    }

                    3 -> {
                        if (mediaPlayer2.isPlaying) {
                            mediaPlayer2.pause()
                        }
                    }
                    18 -> {
                        if (mediaPlayer.isPlaying) {
                            mediaPlayer.pause()
                        }
                    }
                    4 -> {
                        if (!mediaPlayer.isPlaying) {
                            mediaPlayer.start()
                        }
                    }
                }
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Timer Finished", Toast.LENGTH_SHORT).show()
                finish()  // Завершение активности (выход из приложения)
            }
        }.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        timer?.cancel()
    }
}
