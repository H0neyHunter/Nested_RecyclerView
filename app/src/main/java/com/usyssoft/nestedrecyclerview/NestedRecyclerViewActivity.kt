package com.usyssoft.nestedrecyclerview

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.usyssoft.nestedrecyclerview.adapter.firstAdapter
import com.usyssoft.nestedrecyclerview.databinding.ActivityNestedrecyclerviewBinding
import com.usyssoft.nestedrecyclerview.model.firstList
import com.usyssoft.nestedrecyclerview.model.nestedList
import com.usyssoft.nestedrecyclerview.tools.Tools

class NestedRecyclerViewActivity : AppCompatActivity() {
    private lateinit var b : ActivityNestedrecyclerviewBinding
    private lateinit var adapter: firstAdapter
    private var list : ArrayList<firstList> = ArrayList()
    private var listNested : ArrayList<nestedList> = ArrayList()

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityNestedrecyclerviewBinding.inflate(layoutInflater)
        setContentView(b.root)

        context = this@NestedRecyclerViewActivity

        Tools(context).handlerBackPressed {
            finish()
        }

        b.apply {

            backBtn.setOnClickListener {
                Tools(context).onBackPressed()
            }

            rvFirst.setHasFixedSize(true)
            val lm = LinearLayoutManager(this@NestedRecyclerViewActivity)
            rvFirst.layoutManager = lm
            adapter = firstAdapter(this@NestedRecyclerViewActivity,list)
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