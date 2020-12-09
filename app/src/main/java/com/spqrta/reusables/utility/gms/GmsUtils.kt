package com.spqrta.reusables.utility.gms

import io.reactivex.Single

//class TaskFailedException(): Exception()
//
//fun <T> Task<T>.toSingle(): Single<T> {
//    val subject = SingleSubject.create<T>()
//    addOnCompleteListener {
//        if(it.isSuccessful) {
//            subject.onSuccess(it.result)
//        } else {
//            subject.onError(TaskFailedException())
//        }
//    }
//    return subject
//}