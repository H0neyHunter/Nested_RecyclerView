package com.usyssoft.nestedrecyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.usyssoft.nestedrecyclerview.R
import com.usyssoft.nestedrecyclerview.model.firstList

class firstAdapter(private val context : Context,private val list: List<firstList>):RecyclerView.Adapter<firstAdapter.d>() {
    inner class d(v:View):RecyclerView.ViewHolder(v) {
        val header = v.findViewById<TextView>(R.id.textView)
        val rv = v.findViewById<RecyclerView>(R.id.rvNested)
        lateinit var adapterNested: nestedAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): d {
        val l = LayoutInflater.from(context).inflate(R.layout.design_first_layout,parent,false)
        return d(l)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: d, position: Int) {
        val item = list[position]
        holder.apply {
            header.text = item.header

            rv.setHasFixedSize(true)
            val sm = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
            rv.layoutManager = sm
            adapterNested = nestedAdapter(context,item.nestedList)
            rv.adapter = adapterNested
            adapterNested.notifyDataSetChanged()
        }
    }
}
