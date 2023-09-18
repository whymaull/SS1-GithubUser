package com.example.ss1_githubuser.tools

import android.content.Context
import android.view.View
import android.widget.Toast

class Loading {
    fun showLoading(isLoading: Boolean, view: View) {
        if (isLoading) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }

}