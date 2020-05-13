package com.nikolam.basketpro.data

import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable

interface DataSource {

    fun loadDrillTypes() : Observable<DrillsType>

    fun loadDrillList(drillType: String): Observable<Drill>

    fun loadDrillDetails(id: String): io.reactivex.Single<DrillDetail>
}