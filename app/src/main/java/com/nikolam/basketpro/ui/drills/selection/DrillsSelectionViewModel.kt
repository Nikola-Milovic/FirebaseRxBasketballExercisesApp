package com.nikolam.basketpro.ui.drills.selection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.basketpro.data.DrillRepository
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.plusAssign
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DrillsSelectionViewModel(
    private val repository: DrillRepository,
    private val io: CoroutineContext
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _drillsTypeList = MutableLiveData<ArrayList<DrillsType>>()
    val drillsTypeList: LiveData<ArrayList<DrillsType>>
        get() = _drillsTypeList

    init {
        fetchDrillTypes()
    }

    fun fetchDrillTypes() {
        val list = ArrayList<DrillsType>()
        viewModelScope.launch(io) {

            val observer = object : DisposableObserver<DrillsType>() {

                override fun onError(e: Throwable) {
                    Log.d("TAG", "error " + e.message)
                }

                override fun onNext(data: DrillsType) {
                    list.add(data)
                }

                override fun onComplete() {
                    Log.d("TAG", "COMPLETE")
                    _drillsTypeList.postValue(list)
                }
            }

            compositeDisposable += observer

            repository.loadDrillTypes().subscribeWith(observer)
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}
