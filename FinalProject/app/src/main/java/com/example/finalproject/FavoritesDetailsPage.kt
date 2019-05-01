package com.example.finalproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_recipe_details_page.*
import kotlinx.android.synthetic.main.fragment_recipe_details_page.view.*

class FavoritesDetailsPage : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view: View = inflater!!.inflate(R.layout.fragment_recipe_details_page, container, false)

        view.backBtn.setOnClickListener { view ->
            (activity as MainActivity).loadFragment("Favorites")
        }
        var currentTitle = (activity as MainActivity).currentTitle
        if((activity as MainActivity).sp?.getInt(currentTitle, -1) != -1) {
            view.favoritesAddBtn.text = "Remove Favorite"
            println("Item exists in shared preferences, should be set to REMOVE")
        }
        else {
            view.favoritesAddBtn.text = "Add to favorites"
            println("Item does NOT in shared preferences, should be set to ADD")
        }

        var frameHeight: LinearLayout? = view.ingredientsFrame //findViewById(R.id.ingredientsFrame)
        val _height = (activity as MainActivity).getIngredientsLength() * 170
        frameHeight?.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, _height)

        var chars = (activity as MainActivity).wordCount((activity as MainActivity).setInstructionsList().toString())

        var frameHeightInstruct: LinearLayout? = view.instructionsFrame //findViewById(R.id.ingredientsFrame)
        val _heightInstruct = ((activity as MainActivity).getInstructionLength()  * 170) + (chars*11)
        frameHeightInstruct?.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, _heightInstruct)

        view.timerBtn.setOnClickListener { view -> (activity as MainActivity).loadFragment("Timer")}
        view.favoritesAddBtn.setOnClickListener{favoriteButtonClick()}

        // Return the fragment view/layout
        return view
    }
    fun favoriteButtonClick() {
        var currentTitle = (activity as MainActivity).currentTitle
        if((activity as MainActivity).sp?.getInt(currentTitle, -1) != -1) {
            (activity as MainActivity).sp?.edit()?.remove(currentTitle)?.apply()
            var currentT = (activity as MainActivity).currentTitle!!
            (activity as MainActivity).savedRecipes.remove(currentT)
            Toast.makeText(context, "Removed from favorites", Toast.LENGTH_LONG).show()
            favoritesAddBtn?.text = "Add to favorites"
        }
        else {
            var arrayNum = (activity as MainActivity).recipeLoc!!
            var currentT = (activity as MainActivity).currentTitle!!
            (activity as MainActivity).savedRecipes.add(currentT)
            (activity as MainActivity).sp?.edit()?.putInt(currentT, arrayNum)?.apply()
            Toast.makeText(context, "Added to favorites", Toast.LENGTH_LONG).show()
            favoritesAddBtn?.text = "Remove Favorite"
        }
    }
}
