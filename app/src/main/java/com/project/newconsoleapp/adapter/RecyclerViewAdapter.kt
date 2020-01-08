package com.project.newconsoleapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.newconsoleapp.R
import com.project.newconsoleapp.activity.BaseActivity
import com.project.newconsoleapp.api.models.DataList
import com.project.newconsoleapp.fragment.StatusFragment
import kotlinx.android.synthetic.main.view_holder.view.*


/**
 * Created by pawan on 05,December,2019
 */
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var itemList: ArrayList<DataList> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent, false))
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
            itemView.mBotName.text = data.mac
            itemView.setOnClickListener { sendDataIntoFragment(position) }
        }

        private fun sendDataIntoFragment(position: Int){
            val data = itemList[position]

            val activity = itemView.context as BaseActivity
            val args = Bundle()
            args.putString("mac_id", data.mac!!.toString())
            args.putString("joinstamp", data.joinstamp!!.toString())
            args.putString("updatestamp", data.updatestamp!!.toString())
            args.putString("stats", data.stats.screens.toString().replace("[", "").replace("]", ""))
            args.putString("P",data.stats.battery.P.toString())
            args.putString("SOC",data.stats.battery.SOC.toString())
            args.putString("V",data.stats.battery.V.toString())
            args.putString("CE",data.stats.battery.CE.toString())
            args.putString("I",data.stats.battery.I.toString())

            val myFragment = StatusFragment()
            myFragment.arguments = args
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.mFrameContainer, myFragment).addToBackStack(null).commit()
        }

    }

    fun getItemlist(list: ArrayList<DataList>) {
        itemList = list

    }

}