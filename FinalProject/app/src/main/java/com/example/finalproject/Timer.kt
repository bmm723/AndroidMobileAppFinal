package com.example.finalproject

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.os.health.TimerStat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_timer.*
import java.util.Timer
import java.util.concurrent.TimeUnit

class Timer : Fragment() {
    //private var timer: CountDownTimer() = null
    private var numClicks: Int = 0
    private var millisLeft: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val current = (activity as MainActivity).currentPosition
        val total = (activity as MainActivity).myCookTimeList[current!!]
        val cooktime = (total * 60).toLong()
        time.text = total.toString() + ":00:00"
        playButton.setOnClickListener {startTimer(cooktime)}
        pauseButton.isVisible = false
    }

    fun startTimer(cooktime: Long) {
        numClicks++
        var ct = 0L
        if(millisLeft != 0L)
            ct = millisLeft / 1000
        else
            ct = cooktime
        playButton.isVisible = false
        pauseButton.isVisible = true
        if(numClicks == 1) {
            val timer = object : CountDownTimer(ct * 1000, 1) {
                override fun onTick(millisUntilFinished: Long) {
                    var minutes = (millisUntilFinished / 1000) / 60
                    var secondsInMin = ((millisUntilFinished / 1000) - minutes * 60).toString().take(2)
                    var strSec = secondsInMin.toString()
                    var strMin = minutes.toString()
                    var strMilli = (millisUntilFinished%1000).toString().take(2)
                    if(secondsInMin.length != 2)
                        strSec = "0" + secondsInMin
                    if(strMin.length != 2)
                        strMin = "0" + minutes.toString()
                    if(strMilli.length != 2)
                        strMilli = "0" + strMilli
                    millisLeft = millisUntilFinished
                    time?.setText(strMin + ":" + strSec + ":" + strMilli)
                }

                override fun onFinish() {
                    Toast.makeText(context, "Your timer is done!", Toast.LENGTH_LONG)
                }
            }
            pauseButton.setOnClickListener{cancelTimer(timer)}
            timer.start()
        }
        numClicks = 0
    }
    fun cancelTimer(timer: CountDownTimer) {
        playButton.isVisible = true
        pauseButton.isVisible = false

        timer.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
