package com.usyssoft.nestedrecyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.usyssoft.nestedrecyclerview.adapter.nestedScrollAdapter
import com.usyssoft.nestedrecyclerview.databinding.ActivityNedtedScrollViewNestedRecyclerViewBinding
import com.usyssoft.nestedrecyclerview.model.firstList
import com.usyssoft.nestedrecyclerview.model.nestedList
import com.usyssoft.nestedrecyclerview.tools.Tools

class NedtedScrollViewNestedRecyclerViewActivity : AppCompatActivity() {
    private lateinit var b: ActivityNedtedScrollViewNestedRecyclerViewBinding
    private lateinit var context: Context

    private var list: ArrayList<firstList> = ArrayList()
    private var listNested: ArrayList<nestedList> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityNedtedScrollViewNestedRecyclerViewBinding.inflate(layoutInflater)
        setContentView(b.root)
        context = this@NedtedScrollViewNestedRecyclerViewActivity

        Tools(context).handlerBackPressed {
            finish()
        }


        b.apply {
            backBtn2.setOnClickListener {
                Tools(context).onBackPressed()
            }



            getData { completed ->
                if (completed == 1) {
                    val linearContainer = LinearLayout(context)
                    linearContainer.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    linearContainer.orientation = LinearLayout.VERTICAL
                    nestedLayout.addView(linearContainer)
                    for (i in 0 until list.size) {
                        val headerTextView = TextView(context)
                        headerTextView.layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        headerTextView.text = list[i].header
                        linearContainer.addView(headerTextView)
                        linearContainer.addView(createRecyclerView(i))

                    }
                    val goto10item = button
                    goto10item.setOnClickListener {
                        val targetRecyclerViewTag = 10 // Hedef recyclerViewTag değeri
                        for (i in 0 until linearContainer.childCount step 2) {
                            val recyclerView = linearContainer.getChildAt(i + 1) as RecyclerView
                            val recyclerViewTag = recyclerView.tag as? Int

                            if (recyclerViewTag == targetRecyclerViewTag) {
                                nestedLayout.smoothScrollTo(0, recyclerView.top)
                                break
                            }
                        }
                    }

                    val recyclerViewVisibleStates = mutableMapOf<Int, Boolean>()

                    nestedLayout.setOnScrollChangeListener { _, _, _, _, _ ->
                        for (i in 0 until linearContainer.childCount step 2) {
                            val recyclerView = linearContainer.getChildAt(i + 1) as RecyclerView

                            val recyclerViewTop = recyclerView.top - nestedLayout.scrollY
                            val recyclerViewBottom = recyclerViewTop + recyclerView.height

                            val recyclerViewTag = recyclerView.tag as? Int
                            if (recyclerViewTop <= 0 && recyclerViewBottom >= 0) {
                                // RecyclerView görünür durumda
                                if (recyclerViewTag != null && recyclerViewVisibleStates[recyclerViewTag] != true) {
                                    recyclerViewVisibleStates[recyclerViewTag] = true
                                    Log.d("positionRv", recyclerViewTag.toString())
                                }
                            } else {
                                if (recyclerViewTag != null) {
                                    recyclerViewVisibleStates[recyclerViewTag] = false
                                }
                            }
                        }
                    }


                }
            }
        }
    }


    fun getData(completed: (Int) -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            list.clear()
            listNested.clear()

            for (i in 0..200) {
                listNested.add(nestedList(i, "Mockup $i", R.drawable.image))
            }

            for (i in 0..40) {
                list.add(firstList("Header $i", listNested))
            }
            completed(1)
        }, 1000)

    }

    private fun createRecyclerView(listPos: Int): RecyclerView {
        val recyclerView = RecyclerView(context)
        recyclerView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        recyclerView.tag = listPos

        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        val adapter = nestedScrollAdapter(context, list[listPos].nestedList)
        recyclerView.adapter = adapter


        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setItemViewCacheSize(20)
        recyclerView.setRecycledViewPool(RecyclerView.RecycledViewPool())


        return recyclerView
    }

}