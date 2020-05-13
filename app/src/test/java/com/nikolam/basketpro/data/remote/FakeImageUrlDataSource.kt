package com.nikolam.basketpro.data.remote

import io.reactivex.Single

class FakeImageUrlDataSource(val data : String) : ImageUrlDataSource{
    override fun getImageUrl(rawImageUrl: String): Single<String> {
        return Single.just(data)
    }

}