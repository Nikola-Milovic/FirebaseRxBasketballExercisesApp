package com.nikolam.basketpro.data

import com.nikolam.basketpro.data.remote.ImageUrlDataSource
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable
import io.reactivex.Single

class DrillRepository(
    private val remoteDataSource: DataSource,
    private val remoteFilesDataSource: ImageUrlDataSource
) : IDrillRepository {


    override fun loadFullDrillType(): Observable<DrillsType> {
        return loadDrillTypeWithRawImageUrl().flatMapSingle { drillTypeWithRawImageUrl ->
            loadImageUrl(drillTypeWithRawImageUrl.drillType_imageUrl).map {
                return@map drillTypeWithRawImageUrl.copy(drillType_imageUrl = it)
            }
        }
    }

    private fun loadDrillTypeWithRawImageUrl(): Observable<DrillsType> {
        return remoteDataSource.loadDrillTypes()
    }


    private fun loadDrillListWithRawImageUrl(drillType: String): Observable<Drill> {
        return remoteDataSource.loadDrillList(drillType)
    }

    override fun loadFullDrillList(drillType: String) : Observable<Drill>{
        return loadDrillListWithRawImageUrl(drillType).flatMapSingle {drillWithRawImageUrl ->
            loadImageUrl(drillWithRawImageUrl.drillList_imageUrl).map {
                return@map drillWithRawImageUrl.copy(drillList_imageUrl = it)
            }
        }
    }


    override fun loadDrillDetails(id: String): Single<DrillDetail> {
        return remoteDataSource.loadDrillDetails(id)
    }

    private fun loadImageUrl(rawImageUrl: String): Single<String> {
        return remoteFilesDataSource.getImageUrl(rawImageUrl)
    }
}