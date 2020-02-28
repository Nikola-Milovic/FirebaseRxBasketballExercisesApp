package com.nikolam.basketpro.data

import com.nikolam.basketpro.data.remote.ImageUrlDataSource
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class DrillRepository(
    private val remoteDataSource: DataSource,
    private val remoteFilesDataSource: ImageUrlDataSource
) : CoroutineScope {


    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job


    fun loadFullDrillType(): Observable<DrillsType> {
        return loadDrillTypeWithRawImageUrl().flatMap { rawDrillsType ->
            loadImageUrl(rawDrillsType.drillType_imageUrl).map { newImageUrl ->
                DrillsType(rawDrillsType.drillType_title, newImageUrl)
            }
        }
    }


    fun loadDrillTypeWithRawImageUrl(): Observable<DrillsType> {
        return remoteDataSource.loadDrillTypes()
    }

    fun loadDrillDetails(id: String): Single<DrillDetail> {
        return remoteDataSource.loadDrillDetails(id)
    }

    fun loadDrillList(drillType: String): Observable<Drill> {
        return remoteDataSource.loadDrillList(drillType)
    }

    fun loadImageUrl(rawImageUrl: String): io.reactivex.Single<String> {
        return remoteFilesDataSource.getImageUrl(rawImageUrl)
    }
}