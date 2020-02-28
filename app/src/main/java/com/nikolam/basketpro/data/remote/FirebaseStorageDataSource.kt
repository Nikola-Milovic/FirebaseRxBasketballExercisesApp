package com.nikolam.basketpro.data.remote

import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Single

interface ImageUrlDataSource {
    fun getImageUrl(rawImageUrl: String) : Single<String>
}

class FirebaseStorageDataSource(val firebaseStorage: FirebaseStorage) : ImageUrlDataSource {

    override fun getImageUrl(rawImageUrl: String): Single<String>
    {
        return Single.create<String>{emitter->
        firebaseStorage.getReferenceFromUrl(rawImageUrl).downloadUrl.addOnSuccessListener {
            emitter.onSuccess(it.toString())
        }.addOnFailureListener {
            emitter.onError(it)
        }
    }
}
}


