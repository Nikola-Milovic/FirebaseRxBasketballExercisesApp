package com.nikolam.basketpro.ui.drills.selection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.basketpro.data.DrillRepository
import com.nikolam.basketpro.model.DrillsType
import com.nikolam.basketpro.util.SingleLiveEvent
import com.nikolam.basketpro.util.plusAssign
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DrillsSelectionViewModel(private val repository: DrillRepository,
                               private val main : CoroutineContext,
                               private val io : CoroutineContext

) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _drillsTypeList = MutableLiveData<ArrayList<DrillsType>>()
    val drillsTypeList: LiveData<ArrayList<DrillsType>>
        get() = _drillsTypeList


    private var _updateImagesEvent = SingleLiveEvent<Boolean>()

    fun getUpdateImagesEvent(): SingleLiveEvent<Boolean> {
        return _updateImagesEvent
    }

    init {

        fetchDrillTypes()
    }

    fun fetchDrillTypes() {

        val list = ArrayList<DrillsType>()

        viewModelScope.launch(io) {
            compositeDisposable += repository.loadDrillTypes()
                .subscribeWith(object : DisposableObserver<DrillsType>() {

                    override fun onError(e: Throwable) {
                        Log.d("TAG", "error " + e?.message)
                    }

                    override fun onNext(data: DrillsType) {
                        list.add(data)
                    }

                    override fun onComplete() {
                        Log.d("TAG", "COMPLETE")
                        updateImages(list)
                    }
                })
        }


    }


    fun updateImages(list: ArrayList<DrillsType>) {
        viewModelScope.launch(io) {
            list.map {
                async {
                    val newDrillImageUrl = repository.getImageUrl(it.drillType_imageUrl)
                    it.drillType_imageUrl = newDrillImageUrl
                }
            }.awaitAll()
            _drillsTypeList.postValue(list)
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}
