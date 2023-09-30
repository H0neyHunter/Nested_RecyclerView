package com.usyssoft.nestedrecyclerview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.usyssoft.nestedrecyclerview.adapter.firstAdapter
import com.usyssoft.nestedrecyclerview.databinding.ActivityMainBinding
import com.usyssoft.nestedrecyclerview.model.firstList
import com.usyssoft.nestedrecyclerview.model.nestedList

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private lateinit var adapter: firstAdapter
    private var list : ArrayList<firstList> = ArrayList()
    private var listNested : ArrayList<nestedList> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.apply {
            rvFirst.setHasFixedSize(true)
            val lm = LinearLayoutManager(this@MainActivity)
            rvFirst.layoutManager = lm
            adapter = firstAdapter(this@MainActivity,list)
            rvFirst.adapter = adapter
            getData()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        list.clear()
        listNested.clear()

        for (i in 0 .. 200) {
            listNested.add(nestedList(i,"Mockup $i", R.drawable.image))
        }

        for (i in 0 .. 40) {
            list.add(firstList("Header $i",listNested))
        }

        adapter.notifyDataSetChanged()


    }
}