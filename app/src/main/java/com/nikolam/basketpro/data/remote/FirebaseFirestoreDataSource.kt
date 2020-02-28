package com.nikolam.basketpro.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.nikolam.basketpro.data.DataSource
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.model.DrillDetail
import com.nikolam.basketpro.model.DrillsType
import io.reactivex.Observable
import io.reactivex.rxjava3.core.Single

class FirebaseFirestoreDataSource(val firebaseFirestore: FirebaseFirestore) : DataSource {


    override fun loadDrillTypes(): Observable<DrillsType> {
        return Observable.create<DrillsType> { emitter ->
            firebaseFirestore.collection("drilltypes")
                .get()
                .addOnSuccessListener { documents ->
                    try {
                        for (document in documents) {
                            val doc = document.toObject(DrillsType::class.java)
                            emitter.onNext(doc)
                        }
                    } catch (e: Exception) {
                        emitter.onError(e)
                    }
                    emitter.onComplete()
                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                    emitter.onError(exception)
                }
        }
    }


    override fun loadDrillList(drillType: String): Observable<Drill> {
        return Observable.create<Drill> { emitter ->
            firebaseFirestore.collection(drillType + "_list")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        try {
                            val doc = document.toObject(Drill::class.java)
                            emitter.onNext(doc)
                        } catch (e: Exception) {
                            emitter.onError(e)
                        }
                    }
                    emitter.onComplete()
                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                    emitter.onError(exception)
                }
        }
    }

    override fun loadDrillDetails(id : String) : Single<DrillDetail>{
       return Single.create<DrillDetail>{emitter ->
           firebaseFirestore.collection("drills")
               .document(id)
               .get()
               .addOnSuccessListener { document ->
                       try {
                           val doc = document.toObject(DrillDetail::class.java)
                           emitter.onSuccess(doc)
                       } catch (e: Exception) {
                           emitter.onError(e)
                       }
                   }
               .addOnFailureListener { exception ->
                   Log.w("TAG", "Error getting documents: ", exception)
                   emitter.onError(exception)
               }
       }
    }
}
