package com.example.finalproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home_page.view.*

class HomePageFragment : Fragment() {

    var numImg: Int? = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val imgView = view.findViewById<ImageView>(R.id.imgWheel)

        Picasso.get().load((activity as MainActivity).myImgList[numImg!!]).into(imgView)

        view.imgLeftBtn.setOnClickListener { view ->
            if (numImg!! > 0){
                numImg = (numImg!! - 1)
            } else {
                numImg = (activity as MainActivity).myImgList.size-1
            }
            Picasso.get().load((activity as MainActivity).myImgList[numImg!!]).into(imgView)
        }
        view.imgRightBtn.setOnClickListener { view ->
            if (numImg!! >= 0 && numImg!! < (((activity as MainActivity).myImgList).size-1)) {
                numImg = (numImg!! + 1)
            } else {
                numImg = 0
            }
            Picasso.get().load((activity as MainActivity).myImgList[numImg!!]).into(imgView)
        }
    }
}
