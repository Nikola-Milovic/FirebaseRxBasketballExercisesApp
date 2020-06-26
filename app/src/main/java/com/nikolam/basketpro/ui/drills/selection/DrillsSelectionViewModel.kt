package com.nikolam.basketpro.ui.drills.selection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.basketpro.data.IDrillRepository
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DrillsSelectionViewModel(
    private val repository: IDrillRepository,
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

            val disposable = repository.loadFullDrillType()
                .subscribeWith(object : DisposableObserver<DrillsType>() {

                    override fun onError(e: Throwable) {
                        Log.d("TAG", "error " + e.message)
                    }

                    override fun onNext(data: DrillsType) {
                        Log.d("TAG", "data is $data")
                        list.add(data)
                    }

                    override fun onComplete() {
                        Log.d("TAG", "COMPLETE")
                        _drillsTypeList.postValue(list)
                    }
                })

            compositeDisposable.add(disposable)
        }
    }

    fun handleError(bool : Boolean) : Boolean{
      // bool1 = bool
        return bool
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}
