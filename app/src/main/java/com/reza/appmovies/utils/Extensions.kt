package com.reza.appmovies.utils

import android.view.View

fun View.showInVisible(isShow: Boolean) {
    if (isShow) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}