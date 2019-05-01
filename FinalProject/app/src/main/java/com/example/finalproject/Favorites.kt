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

class Favorites : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }
}
