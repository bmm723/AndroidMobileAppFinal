package com.example.finalproject


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.ListFragment

class DetailsList : ListFragment() {

    var currentPosition: Int? = 0
//
    var currentTitle: String? = null
    var currentTime: String? = null
    var currentLevel: String? = null
//
   private var itemsArrayList = arrayListOf<String>()

 //   val itemsArrayList = arrayListOf("C", "E", "F")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        currentPosition = (activity as MainActivity).currentPosition
        currentTitle = (activity as MainActivity).myTitlesList[currentPosition!!]
        currentTime = (activity as MainActivity).myTimeList[currentPosition!!]
        currentLevel = (activity as MainActivity).myLevelList[currentPosition!!]

        itemsArrayList = arrayListOf(currentTitle.toString(), currentTime.toString(), currentLevel.toString())


        listAdapter = ItemsArrayAdapter (this.context!!,
            R.layout.fragment_details_list,
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
                holder.detailTextView = retView.findViewById<TextView>(R.id.detail_list)
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
