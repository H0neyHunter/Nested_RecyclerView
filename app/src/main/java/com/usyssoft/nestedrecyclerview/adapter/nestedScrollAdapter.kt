package com.usyssoft.nestedrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.usyssoft.nestedrecyclerview.R
import com.usyssoft.nestedrecyclerview.model.nestedList

class nestedScrollAdapter(private val context: Context, private val list: List<nestedList>):RecyclerView.Adapter<nestedScrollAdapter.d>() {
    inner class d(v:View):RecyclerView.ViewHolder(v) {
        val imageName = v.findViewById<TextView>(R.id.textView3)
        val image = v.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): d {
        val l = LayoutInflater.from(context).inflate(R.layout.design_nested_layout,parent,false)
        return d(l)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: d, position: Int) {
        val item = list[position]
        holder.apply {
            imageName.text = item.imageName
            image.setImageDrawable(ContextCompat.getDrawable(context,item.imageDrawable))

        }
    }
}