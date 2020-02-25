package com.nikolam.basketpro.data.remote

import com.google.firebase.storage.FirebaseStorage
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseStorageDataSource(val firebaseStorage: FirebaseStorage){

    val storageRef = firebaseStorage.reference

    suspend fun getImageUrl(storageLocation : String) : String = suspendCoroutine {cont ->
            firebaseStorage.getReferenceFromUrl(storageLocation).downloadUrl.addOnSuccessListener {
                cont.resume(it.toString())
            }.addOnFailureListener {
                cont.resumeWithException(it)
            }
        }
}


