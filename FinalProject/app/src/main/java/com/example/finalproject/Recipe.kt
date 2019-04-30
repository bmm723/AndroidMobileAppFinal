package com.example.finalproject

class Recipe {
    private var title: String? = null
    private var type: String? = null
    private var level: String? = null
    private var timeTotal: String? = null
    private var cookTime: Int? = null
    private var servings: Int? = null
    private var calories: Int? = null
    private var rating: Int? = null
    private var imageLink: String? = null
    private var sourceLink: String? = null
    private var ingredients = ArrayList<String>()
    private var instructions = ArrayList<String>()

    fun Recipe() {

    }
    fun Recipe(title:String, type:String, level:String, timeTotal:String,
               cookTime:Int, servings:Int, calories:Int, rating:Int, imageLink:String,
               sourceLink:String, ingredients:ArrayList<String>, instructions:ArrayList<String>) {
        this.title = title
        this.type = type
        this.level = level
        this.timeTotal = timeTotal
        this.cookTime = cookTime
        this.servings = servings
        this.calories = calories
        this.rating = rating
        this.imageLink = imageLink
        this.sourceLink = sourceLink
        this.ingredients = ingredients
        this.instructions = instructions
    }

}