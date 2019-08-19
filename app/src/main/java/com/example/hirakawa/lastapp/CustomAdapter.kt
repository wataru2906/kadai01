package com.example.hirakawa.lastapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

data class dataarray(val name:String,val pass:String)

class CustomAdapter (private val context: Context,private val saitopass: List<dataarray>) : BaseAdapter() {

    private  val inflater = LayoutInflater.from(context)

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = p1 ?:createView(p2)

        val hirakawa = getItem(p0)

        val viewHolder:ViewHolder = view.tag as ViewHolder

        viewHolder.saname.text = hirakawa.name
        viewHolder.paname.text = hirakawa.pass
     //   viewHolder.PaName.text = user.PaNa

        return  view
    }

    override fun getItem(p0: Int) = saitopass[p0]
    override fun getItemId(p0: Int) = p0.toLong()
    override fun getCount() = saitopass.size


    private class ViewHolder(view:View){
        val saname = view.findViewById(R.id.textView) as TextView
        val paname = view.findViewById(R.id.textView2) as TextView
       // val PaName = view.findViewById(R.id.PaName) as TextView
    }
    private fun createView( parent : ViewGroup?) : View{
        val view:View = inflater.inflate(R.layout.list_text_row,parent,false)
        view.tag = ViewHolder(view)
        return view

    }


}