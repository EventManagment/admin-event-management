package com.admin.admineventmanagement.data.network

import com.admin.admineventmanagement.data.models.AdminUser
import com.admin.admineventmanagement.data.models.UserEvent
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://jsonplaceholder.typicode.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UserEventApiService {
    @GET("users")
    suspend fun getEventUser() : MutableList<UserEvent>
}

object UserEventApi {
    val retrofitService: UserEventApiService by lazy { retrofit.create(UserEventApiService::class.java) }
}

interface AdminApiService {
    @GET("users")
    suspend fun getAdminUser() : AdminUser
}

object AdminApi {
    val retrofitService: AdminApiService by lazy { retrofit.create(AdminApiService::class.java) }
}