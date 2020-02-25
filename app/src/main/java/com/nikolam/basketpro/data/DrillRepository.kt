package com.nikolam.basketpro.data

import com.nikolam.basketpro.data.remote.FirebaseStorageDataSource
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable
import io.reactivex.rxjava3.core.Single

class DrillRepository( private val remoteDataSource : DataSource, private val remoteFilesDataSource: FirebaseStorageDataSource ) {


    fun loadDrillTypes(): Observable<DrillsType> {
        return remoteDataSource.loadDrillTypes()
    }


    fun loadDrillDetails(id : String): Single<DrillDetail> {
        return remoteDataSource.loadDrillDetails(id)
    }


    fun loadDrillList(drillType : String): Observable<Drill> {
        return remoteDataSource.loadDrillList(drillType)
    }

    suspend fun getImageUrl(storageLocation : String) : String
    {
        return remoteFilesDataSource.getImageUrl(storageLocation)
    }
}