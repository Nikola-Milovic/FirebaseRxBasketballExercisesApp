package com.nikolam.basketpro.util

import android.content.Context
import android.net.ConnectivityManager


inline fun isNetworkAvailable(context : Context) : Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetwork
    return activeNetwork != null
}