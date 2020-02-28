package com.nikolam.basketpro.data

import com.nikolam.basketpro.data.remote.ImageUrlDataSource
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test

internal class DrillRepositoryTest {
    @Test
    internal fun `will return model with updated image urls`() {

        val expectedModel = DrillsType("XXX", "YYY")
        val repository = DrillRepository(object : DataSource {
            override fun loadDrillTypes(): Observable<DrillsType> = Observable.just(DrillsType("XXX", "XXX"))

            override fun loadDrillList(drillType: String): Observable<Drill> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadDrillDetails(id: String): Single<DrillDetail> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }, object : ImageUrlDataSource {
            override fun getImageUrl(rawImageUrl: String): String {
                return "YYY"
            }
        })

        val testObserver = repository.loadDrillTypeWithRawImageUrl().test()

        testObserver.assertValues(expectedModel)

    }
}