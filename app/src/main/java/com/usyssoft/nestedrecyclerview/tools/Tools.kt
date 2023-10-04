package com.usyssoft.nestedrecyclerview.tools

import android.app.Activity
import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner

class Tools(private val context: Context) {

    fun handlerBackPressed(completed : (Int)->Unit){
        if (context is OnBackPressedDispatcherOwner){
            context.onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    completed(1)
                }
            })
        }
    }
    fun onBackPressed(){
        if (context is OnBackPressedDispatcherOwner) {
            context.onBackPressedDispatcher.onBackPressed()
        }
    }
}