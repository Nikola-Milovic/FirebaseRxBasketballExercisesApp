package com.nikolam.basketpro.data.remote

import com.nikolam.basketpro.data.DataSource
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable

class FakeRemoteDataSource (val answer : DrillsType) : DataSource {
        override fun loadDrillTypes(): Observable<DrillsType> = Observable.just(answer)

        override fun loadDrillList(drillType: String): Observable<Drill> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun loadDrillDetails(id: String): io.reactivex.rxjava3.core.Single<DrillDetail> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
}
