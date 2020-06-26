package com.nikolam.basketpro.data

import com.nikolam.basketpro.data.remote.ImageUrlDataSource
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.jupiter.api.Test

internal class DrillRepositoryTest {

    @Test
    fun `will return drill type model with updated image urls`() {
        val expectedModel = DrillsType("XXX", "YYY")
        val repository = DrillRepository(object : DataSource {
            override fun loadDrillTypes(): Observable<DrillsType> = Observable.just(DrillsType("XXX", "XXX"))

            override fun loadDrillList(drillType: String): Observable<Drill> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadDrillDetails(id: String): Single <DrillDetail> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }, object : ImageUrlDataSource {
            override fun getImageUrl(rawImageUrl: String): Single<String> {
               return Single.just("YYY")
            }
        })

        val testObserver = repository.loadFullDrillType().test()

        testObserver.assertValues(expectedModel)
    }


    @Test
    fun `will return drill list model with updated image urls`() {
        val expectedModel = Drill("YYY", "ID", "Description", "Title")
        val givenModel = Drill("XXX", "ID", "Description", "Title")

        val repository = DrillRepository(object : DataSource {
            override fun loadDrillTypes(): Observable<DrillsType> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadDrillList(drillType: String): Observable<Drill> = Observable.just(givenModel)

            override fun loadDrillDetails(id: String): Single <DrillDetail> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }, object : ImageUrlDataSource {
            override fun getImageUrl(rawImageUrl: String): Single<String> {
                return Single.just("YYY")
            }
        })

        val testObserver = repository.loadFullDrillList("footwork").test()

        testObserver.assertValues(expectedModel)
    }
}