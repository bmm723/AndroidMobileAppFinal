package com.example.finalproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_recipe_details_page.*
import kotlinx.android.synthetic.main.fragment_recipe_details_page.view.*

class RecipeDetailsPage : Fragment() {

    //var sPref: SharedPreferences? = (activity as MainActivity).sp

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view: View = inflater!!.inflate(R.layout.fragment_recipe_details_page, container, false)

        view.backBtn.setOnClickListener { view ->
            (activity as MainActivity).loadFragment("List")
        }

        var frameHeight: LinearLayout? = view.ingredientsFrame //findViewById(R.id.ingredientsFrame)
        val _height = (activity as MainActivity).getIngredientsLength() * 170
        frameHeight?.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, _height)

        var chars = (activity as MainActivity).wordCount((activity as MainActivity).setInstructionsList().toString())

        var frameHeightInstruct: LinearLayout? = view.instructionsFrame //findViewById(R.id.ingredientsFrame)
        val _heightInstruct = ((activity as MainActivity).getInstructionLength()  * 170) + (chars*11)
                frameHeightInstruct?.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, _heightInstruct)

        view.timerBtn.setOnClickListener { view -> (activity as MainActivity).loadFragment("Timer")}
        //favoritesAddBtn.setOnClickListener{favoriteButtonClick()}
        // Return the fragment view/layout
        return view
    }
    /*fun favoriteButtonClick() {
        sPref!!.edit().putString("recipe", R.id.title.toString()).apply()
        //println(sp?.getString("recipe", ""))
        Toast.makeText(context, "Added to favorites", Toast.LENGTH_LONG).show()
    }*/
}
