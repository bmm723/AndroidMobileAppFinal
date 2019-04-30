package com.example.finalproject

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

class DetailImage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val current = (activity as MainActivity).currentPosition
        Picasso.get().load((activity as MainActivity).myImgList[current!!]).into(view.findViewById<ImageView>(R.id.detail_img))
    }
}
