package com.example.finalproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recipe_details_page.*
import org.json.JSONArray
import org.json.JSONException
import android.R.id.edit




class MainActivity : AppCompatActivity() {

    public var spEdit: SharedPreferences.Editor? = null
    var sp: SharedPreferences? = null
    public var currentTitle: String? = null
    public var recipeLoc: Int? = null
    public var savedRecipes = ArrayList<String>()
    public var contextOfApp: Context? = null

    public val myTitlesList = ArrayList<String>()
    public val myCookTimeList = ArrayList<Int>()
    public val myRatingsList = ArrayList<String>()
    public val myTimeList = ArrayList<String>()
    public val myImgList = ArrayList<String>()
    public val myLevelList = ArrayList<String>()

    public var myIngredientList = ArrayList<JSONArray>()
    public var myInstructionsList = ArrayList<JSONArray>()


    public var currentPosition: Int? = 0

    private var myListFragment: RecipeCards? = null
    private var favoritesList: FavoritesList? = null


    fun loadJSONFromAsset(){

        val json: String = applicationContext.assets.open("Recipes.json").bufferedReader().use{
            it.readText()
        }

        try {
            val titles = JSONArray(json)

            //Loop the Array
            for (i in 0 until titles.length()) {
                Log.e("Message", "loop")
                val map = HashMap<String, String>()
                val e = titles.getJSONObject(i)
                //map["id"] = "id"
                myTitlesList.add(e.getString("title"))
                myCookTimeList.add(e.getInt("cookTime"))
                myRatingsList.add(e.getString("rating"))
                myTimeList.add(e.getString("timeTotal"))
                myImgList.add(e.getString("imageLink"))
                myLevelList.add(e.getString("level"))
                //myIngredientList.add(e.getString("ingredients"))
                val foodJson = e.getJSONArray("ingredients")
                myIngredientList.add(foodJson!!)
                val instructJson = e.getJSONArray("instructions")
                myInstructionsList.add(instructJson!!)

            }
        } catch (e: JSONException) {
            Log.e("log_tag", "Error parsing data $e")
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.the_info, HomePageFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_recipes -> {
                loadFragment("List")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_fav -> {
                loadFragment("Favorites")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                loadFragment("Search")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    public fun loadFragment(which: String){
        if(which =="List") {
            myListFragment = RecipeCards()
            myListFragment?.setMyItemChangedListener(itemChangeListener)
            println("Assigning the listener")

            supportFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            ) //clear backstack
            supportFragmentManager.beginTransaction()
                .replace(R.id.the_info, myListFragment!!)
                .commit()
        }
        if(which =="Search") {
            myListFragment = RecipeCards()
            myListFragment?.setMyItemChangedListener(itemChangeListener)

            supportFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            ) //clear backstack

            supportFragmentManager.beginTransaction().replace(R.id.the_info, Search()).commit()
            supportFragmentManager.beginTransaction()
                .replace(R.id.search_result, myListFragment!!)
                .commit()
        }
        if(which == "Timer") {
            supportFragmentManager.beginTransaction().replace(R.id.the_info, Timer()).commit()
        }
        if(which == "Favorites") {
            favoritesList = FavoritesList()
            favoritesList?.setMyItemChangedListener(favItemChangeListener)

            supportFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            ) //clear backstack
            supportFragmentManager.beginTransaction()
                .replace(R.id.the_info, favoritesList!!)
                .commit()
        }
    }


    //listener for list fragment
    private var itemChangeListener: RecipeCards.ItemChangedListener =
        object : RecipeCards.ItemChangedListener {

            override fun onSelectedItemChanged(itemNameInt: Int) {
                currentPosition = itemNameInt

                supportFragmentManager.beginTransaction().replace(R.id.the_info, RecipeDetailsPage())
                    .commit()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.detailsFrame, DetailsList()).commit()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.ingredientsFrame, IngredientsList()).commit()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.detail_image, DetailImage()).commit()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.instructionsFrame, InstructionsList()).commit()
            }

        }
    private var favItemChangeListener: FavoritesList.ItemChangedListener =
        object : FavoritesList.ItemChangedListener {

            override fun onSelectedItemChanged(itemNameInt: Int) {
                currentPosition = itemNameInt

                supportFragmentManager.beginTransaction().replace(R.id.the_info, FavoritesDetailsPage())
                    .commit()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.detailsFrame, DetailsList()).commit()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.ingredientsFrame, IngredientsList()).commit()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.detail_image, DetailImage()).commit()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.instructionsFrame, InstructionsList()).commit()
            }

        }

    fun getIngredientsLength(): Int{
        return setIngredientsList().size
    }


    fun setIngredientsList(): ArrayList<String> {
        var currentIngredients = myIngredientList[currentPosition!!]

        val curr = currentIngredients

        val list = ArrayList<String>()
        for (i in 0 until curr!!.length()) {
            list.add(curr[i].toString())
        }
        return list
    }

    fun getInstructionLength(): Int{
        return setInstructionsList().size
    }

    fun setInstructionsList(): ArrayList<String> {
        var currentInstructions = myInstructionsList[currentPosition!!]

        val curr = currentInstructions

        val list = ArrayList<String>()
        for (i in 0 until curr!!.length()) {
            list.add(curr[i].toString())
        }
        return list
    }

    //Code taken from https://stackoverflow.com/questions/8924599/how-to-count-the-exact-number-of-words-in-a-string-that-has-empty-spaces-between
    fun wordCount(word: String?): Int {

        if (word == null || word.trim { it <= ' ' }.length == 0) {
            return 0
        }

        var counter = 1

        for (c in word.trim { it <= ' ' }.toCharArray()) {
            if (c == ' ') {
                counter++
            }
        }
        return counter
    }
    fun addToList(results: ArrayList<Int>?) {
        var len = results?.size
        if (len != 0) {
            println("add run: " + myTitlesList[results!!.get(0)])
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contextOfApp = getApplicationContext()
        sp = getSharedPreferences("Favorites", Context.MODE_PRIVATE)
        //spEdit = sp.edit()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.the_info,HomePageFragment()).commit()
            loadJSONFromAsset()
        }
        val editor = sp?.edit()?.clear()?.commit()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
