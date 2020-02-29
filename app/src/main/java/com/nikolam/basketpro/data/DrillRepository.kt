package com.nikolam.basketpro.data

import com.nikolam.basketpro.data.remote.ImageUrlDataSource
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable
import io.reactivex.rxjava3.core.Single

class DrillRepository(
    private val remoteDataSource: DataSource,
    private val remoteFilesDataSource: ImageUrlDataSource
)  {


    fun loadFullDrillType(): Observable<DrillsType> {
        return loadDrillTypeWithRawImageUrl().flatMapSingle { drillTypeWithRawImageUrl ->
            loadImageUrl(drillTypeWithRawImageUrl.drillType_imageUrl).map {
                return@map drillTypeWithRawImageUrl.copy(drillType_imageUrl = it)
            }
        }
    }

    fun loadDrillTypeWithRawImageUrl(): Observable<DrillsType> {
        return remoteDataSource.loadDrillTypes()
    }


    fun loadDrillListWithRawImageUrl(drillType: String): Observable<Drill> {
        return remoteDataSource.loadDrillList(drillType)
    }

    fun loadFullDrillList(drillType: String) : Observable<Drill>{
        return loadDrillListWithRawImageUrl(drillType).flatMapSingle {drillWithRawImageUrl ->
            loadImageUrl(drillWithRawImageUrl.drillList_imageUrl).map {
                return@map drillWithRawImageUrl.copy(drillList_imageUrl = it)
            }
        }
    }


    fun loadDrillDetails(id: String): Single<DrillDetail> {
        return remoteDataSource.loadDrillDetails(id)
    }

    fun loadImageUrl(rawImageUrl: String): io.reactivex.Single<String> {
        return remoteFilesDataSource.getImageUrl(rawImageUrl)
    }
}