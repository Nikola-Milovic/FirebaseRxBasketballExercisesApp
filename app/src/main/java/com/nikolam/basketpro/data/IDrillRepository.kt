package com.nikolam.basketpro.data

import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable
import io.reactivex.Single

interface IDrillRepository {
    fun loadFullDrillType(): Observable<DrillsType>
    fun loadFullDrillList(drillType: String) : Observable<Drill>
    fun loadDrillDetails(id: String): Single<DrillDetail>
}