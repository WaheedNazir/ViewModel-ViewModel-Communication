package com.waheed.mvvmpoc.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * Created by Waheed on 26,November,2019
 */

object KeyboardUtils {

    fun hideKeyboard(activity: Activity?) {
        if (activity == null) return
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(activity: Activity?) {
        if (activity == null) return
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun hideSoftKeyboard(activity: Activity?) {
        if (activity == null) return
        val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        activity.currentFocus?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    fun hideKeyBoardWhenClickOutSide(view: View?, activity: Activity?) {
        if (activity == null || view == null) return
        if (view !is EditText && view !is Button) {
            view.setOnTouchListener { _, _ ->
                //                    v.performClick();
                hideSoftKeyboard(activity)
                false
            }
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                hideKeyBoardWhenClickOutSide(innerView, activity)
            }
        }
    }

    fun hideKeyBoardWhenClickOutSideText(view: View?, activity: Activity?) {
        if (activity == null || view == null) return
        if (view !is EditText && view !is Button && view !is TextView) {
            view.setOnTouchListener { _, _ ->
                //                    v.performClick();
                hideSoftKeyboard(activity)
                false
            }
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                hideKeyBoardWhenClickOutSideText(innerView, activity)
            }
        }
    }

    fun closeSoftKeyboard(activity: Activity?) {
        if (activity == null) return
        val currentFocusView = activity.currentFocus
        currentFocusView?.let {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    fun hideKeyboardInDialogFragment(view: View?) {
        if (view == null) return
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun forceShowKeyboard(view: View?) {
        if (view == null) return
        view.requestFocus()
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}