package com.nikolam.basketpro.ui.drills.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.basketpro.data.IDrillRepository
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.util.plusAssign
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrillsListViewModel(val repository: IDrillRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    //Used for displaying the current quote
    private var _drillsList = MutableLiveData<ArrayList<Drill>>()
    val drillsList: LiveData<ArrayList<Drill>>
        get() = _drillsList


    fun fetchDrillList(drillType : String)
    {
        val list = ArrayList<Drill>()

        viewModelScope.launch(Dispatchers.IO) {
            compositeDisposable += repository.loadFullDrillList(drillType)
                .subscribeWith(object : DisposableObserver<Drill>() {

                    override fun onError(e: Throwable) {
                        Log.d("TAG", e.message)
                    }

                    override fun onNext(data: Drill) {
                        list.add(data)
                    }

                    override fun onComplete() {
                        _drillsList.postValue(list)
                    }
                })
        }
    }


    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }


}
