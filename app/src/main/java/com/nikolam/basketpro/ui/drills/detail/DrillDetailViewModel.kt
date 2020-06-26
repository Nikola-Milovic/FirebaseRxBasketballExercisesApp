package com.nikolam.basketpro.ui.drills.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.basketpro.data.IDrillRepository
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DrillDetailViewModel(val repository: IDrillRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()


    private var _playVideoEvent = SingleLiveEvent<String>()

    fun getPlayVideoEvent(): SingleLiveEvent<String> {
        return _playVideoEvent
    }


    fun getDrillDetails(id: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val disposable =  repository.loadDrillDetails(id).subscribeWith(object : io.reactivex.observers.DisposableSingleObserver<DrillDetail>(),
                Disposable {
                override fun onSuccess(t: DrillDetail) {
                    Log.d("TAG", t.toString())
                    _playVideoEvent.postValue(t.drill_videoUrl)
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "message is + " + e.message)
                }
            })

             compositeDisposable.add(disposable)


        }
    }


    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
