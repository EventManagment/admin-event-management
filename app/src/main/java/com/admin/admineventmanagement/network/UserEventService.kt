package com.admin.admineventmanagement.network

import com.admin.admineventmanagement.model.UserEvent
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UserEventService {
    @GET("photos")
    suspend fun getPhotos() : List<UserEvent>
}

object MarsApi {
    val retrofitService: UserEventService by lazy { retrofit.create(UserEventService::class.java) }
}
