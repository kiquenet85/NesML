package com.nesml.commons.navigation

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.random.Random

class Navigator constructor(private val activity: AppCompatActivity) {

    val random = Random(1000).nextInt()

    fun navigateTo(fragment: Fragment) {
        Log.e(Navigator::class.java.simpleName, "My number is $random")
    }

    fun navigateTo(activity: AppCompatActivity) {
        Log.e(Navigator::class.java.simpleName, "My number is $random")
    }

}