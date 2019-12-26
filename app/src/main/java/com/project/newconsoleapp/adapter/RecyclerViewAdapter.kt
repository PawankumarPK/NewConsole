package com.project.newconsoleapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.newconsoleapp.R
import com.project.newconsoleapp.activity.BaseActivity
import com.project.newconsoleapp.api.models.DataList
import kotlinx.android.synthetic.main.view_holder.view.*


/**
 * Created by pawan on 05,December,2019
 */
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var itemList: ArrayList<DataList> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder,
                parent,
                false
            )
        )
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
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Item is clicked ${data.mac}", Toast.LENGTH_SHORT).show()

                val activity = itemView.context as BaseActivity
                val args = Bundle()
                args.putString("mac_id", data.mac)
                val myFragment = Core0Fragment()
                myFragment.arguments = args
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.mFrameContainer, myFragment).addToBackStack(null).commit()


            }

        }

    }

    private fun demoFun() {
        // val singleAmount = Integer.parseInt(carSingleAmount.text.toString())


        /*  val args = Bundle()
          args.putString("doctor_id", carType.text.toString())
          val newFragment = CarDetails()
          newFragment.arguments = args

          fragmentManager!!.beginTransaction().replace(R.id.containerView, newFragment).commit()*/
    }


    fun getDataIntoItemlist(list: ArrayList<DataList>) {
        itemList = list

    }

}