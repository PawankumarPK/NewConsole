package com.project.newconsoleapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.newconsoleapp.R
import com.project.newconsoleapp.api.models.DataList
import kotlinx.android.synthetic.main.view_holder.view.*

/**
 * Created by pawan on 05,December,2019
 */
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var itemList: ArrayList<DataList> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder,parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(position)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(position: Int) {
            val data = itemList[position]
            itemView.mRecyclerTextView.text = data.mac
        }

    }

     fun getDataIntoItemlist(list: ArrayList<DataList>){
        itemList = list

    }

}