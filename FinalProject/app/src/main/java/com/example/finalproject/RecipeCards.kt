package com.example.finalproject


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.ListFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recipe_cards.*

class RecipeCards : ListFragment() {

    var itemChangedListener: ItemChangedListener? = null

    //interface for the listenerfor changes to selected item
    interface ItemChangedListener{
        fun onSelectedItemChanged(itemNameInt: Int)
    }

    fun setMyItemChangedListener(listener: ItemChangedListener){
        itemChangedListener = listener
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listAdapter = ItemsArrayAdapter (this.context!!,
            R.layout.fragment_recipe_cards,
            (activity as MainActivity).myTitlesList)

        //allow one item to be selected at a time
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.itemsCanFocus = true
        listView.setBackgroundColor(Color.BLACK)
        listView.onItemClickListener = itemsOnItemClickListener
    }

    //custom arrayAdapter
    inner class ItemsArrayAdapter(context: Context, resource: Int,
                                  list: ArrayList<String>): ArrayAdapter<String>(context,resource, list){

        var resource: Int
        var list: ArrayList<String>
        var vi: LayoutInflater

        init {
            this.resource = resource
            this.list = list
            this.vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                    as LayoutInflater
        }

        //get ListView item for the given position

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var holder: ViewHolder //hold reference to the current item's GUI
            var view: View

            if(convertView == null){
                //inflate a new one
                view = vi.inflate(resource, null)
                //view = inflater.inflate(R.layout.fragment_recipe_cards, parent, false)


                //set it up
                holder = ViewHolder()
                holder.itemTextView = view.findViewById(R.id.recipe_card)
                holder.titleTextView = view.findViewById(R.id.recipe_name) as TextView
                holder.timeTextView = view.findViewById(R.id.recipe_time) as TextView
                holder.ratingTextView = view.findViewById(R.id.recipe_rating) as TextView
                holder.dessertImageView= view.findViewById(R.id.recipeImg) as ImageView


                view.tag = holder

            } else{
                //get the viewholder from the current view's tag
                holder = convertView.tag as ViewHolder
                view = convertView
            }

            val titleTextView = holder.titleTextView
            val timeTextView = holder.timeTextView
            val ratingTextView = holder.ratingTextView
            val dessertImageView = holder.dessertImageView

            titleTextView?.text = (activity as MainActivity).myTitlesList[position]
            timeTextView?.text = (activity as MainActivity).myTimeList[position]
            ratingTextView?.findViewById<TextView>(R.id.recipe_rating)?.text = ("Rating: ${(activity as MainActivity).myRatingsList[position]}")
            if (dessertImageView != null){
                Picasso.get().load((activity as MainActivity).myImgList[position])
                    .placeholder(R.drawable.placeholder)
                    .into(dessertImageView)
            }


            return view

        } //getView

    } //inner class

    //define the Gui for each item
    internal class ViewHolder{

        var itemTextView: CardView? = null
        var titleTextView: TextView? = null
        var timeTextView: TextView? = null
        var ratingTextView: TextView? = null
        var dessertImageView: ImageView? = null
    }

    private val itemsOnItemClickListener: AdapterView.OnItemClickListener =
        AdapterView.OnItemClickListener{ adapterView, view, position, id ->

            itemChangedListener?.onSelectedItemChanged(position)
        }


}
