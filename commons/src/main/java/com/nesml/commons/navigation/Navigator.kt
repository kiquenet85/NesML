package com.nesml.commons.navigation

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nesml.commons.R
import com.nesml.commons.util.EMPTY_STRING

class Navigator(private val activity: AppCompatActivity) {

    fun navigateTo(fragment: Fragment) {
        Log.v(
            Navigator::class.java.simpleName,
            "Navigating to Fragment ${fragment::class.java.canonicalName}"
        )
        pushFragment(
            fragment,
            true,
            R.id.frag_container,
            fragment::class.java.canonicalName ?: EMPTY_STRING
        )
    }

    fun navigateTo(context: Context, javaClass: Class<*>) {
        Log.v(
            Navigator::class.java.simpleName,
            "Navigating to AppCompatActivity ${javaClass.canonicalName}"
        )
        val intent = Intent(context, javaClass)
        activity.startActivity(intent)
    }

    private fun pushFragment(
        fragment: Fragment, addToBackStack: Boolean,
        containerId: Int, tag: String, usingAddTransaction: Boolean = false
    ): Int {

        val transaction = activity.supportFragmentManager.beginTransaction()
        if (addToBackStack && activity.supportFragmentManager.backStackEntryCount > 1) {
            transaction.addToBackStack(tag)
        }

        // use commitAllowingStateLoss per Support Library bug: https://code.google.com/p/android/issues/detail?id=19917
        return if (usingAddTransaction) {
            transaction.add(containerId, fragment, tag).commitAllowingStateLoss()
        } else {
            transaction.replace(containerId, fragment, tag)?.commitAllowingStateLoss()
        }
    }
}