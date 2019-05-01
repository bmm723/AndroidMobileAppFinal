package com.example.finalproject


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import java.io.Console
import java.lang.StringBuilder

class Search : Fragment() {
    private var list: ArrayList<String>? = null
    public var results: ArrayList<Int>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list = (activity as MainActivity).myTitlesList
        searchInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                results?.clear()
                var j = 0
                for(i in list!!) {
                    var word = searchInput.text.toString()
                    val findRes = Regex(word).find(i)
                    if(findRes?.value != null) {
                        results?.add(j)
                        (activity as MainActivity).addToList(results)
                    }
                    j++
                }
            }
        })
        // update RecipeCards
    }
}
