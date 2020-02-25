package com.nikolam.basketpro.data

import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable
import io.reactivex.rxjava3.core.Single

interface DataSource {

    fun loadDrillTypes() : Observable<DrillsType>

    fun loadDrillList(drillType: String): Observable<Drill>

    fun loadDrillDetails(id: String): Single<DrillDetail>
}