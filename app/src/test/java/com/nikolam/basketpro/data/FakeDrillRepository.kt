package com.nikolam.basketpro.data

import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable
import io.reactivex.Single

class FakeDrillRepository : IDrillRepository{
    override fun loadFullDrillType(): Observable<DrillsType> = Observable.just(DrillsType("title1", "url1"), DrillsType("title2", "url2"))

    override fun loadFullDrillList(drillType: String): Observable<Drill> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDrillDetails(id: String): Single<DrillDetail> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}