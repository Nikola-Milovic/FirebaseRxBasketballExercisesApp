package com.nikolam.basketpro.util

import android.view.View
import com.nikolam.basketpro.model.Drill

interface DrillsClickListener {
    fun recyclerViewListClicked(id: String, view: View)

}
interface DrillListClickListener {
    fun recyclerViewListClicked(drill : Drill, view: View)
}