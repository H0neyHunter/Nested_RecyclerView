package com.usyssoft.nestedrecyclerview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.usyssoft.nestedrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        context = this@MainActivity

        b.apply {
            nestedRv.setOnClickListener {
                startActivity(Intent(context,NestedRecyclerViewActivity::class.java))
            }
            scrollRvNested.setOnClickListener {
                startActivity(Intent(context,NedtedScrollViewNestedRecyclerViewActivity::class.java))
            }
        }

    }
}