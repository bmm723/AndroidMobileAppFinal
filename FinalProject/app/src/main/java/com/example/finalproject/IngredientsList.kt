package com.example.finalproject


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.ListFragment
import kotlin.collections.ArrayList

class IngredientsList : ListFragment() {

    var currentPosition: Int? = 0

    private var itemsArrayList = arrayListOf<String>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        currentPosition = (activity as MainActivity).currentPosition

        itemsArrayList = (activity as MainActivity).setIngredientsList() //arrayListOf("A", "b") //(activity as MainActivity).myIngredientList

        listAdapter = ItemsArrayAdapter (this.context!!,
            R.layout.fragment_ingredients_list,
            itemsArrayList)

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
            var retView: View

            if(convertView == null){
                //inflate a new one
                retView = vi.inflate(resource, null)

                //set it up
                holder = ViewHolder()
                holder.detailTextView = retView.findViewById<TextView>(R.id.ingredients)
                retView.tag = holder

            } else{
                //get the viewholder from the current view's tag
                holder = convertView.tag as ViewHolder
                retView = convertView
            }

            val item = list[position]
            holder.detailTextView?.text = item

            return retView

        } //getView

    } //inner class

    //define the Gui for each item
    internal class ViewHolder{

        var detailTextView: TextView? = null
    }


}
