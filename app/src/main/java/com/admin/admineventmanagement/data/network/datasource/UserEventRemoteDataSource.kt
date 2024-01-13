package com.admin.admineventmanagement.data.network.datasource

class UserEventRemoteDataSource(
//    private val userEventApiService: UserEventApiService,
//    private val ioDispatcher: CoroutineDispatcher
) {
    /**
     * Fetches the latest news from the network and returns the result.
     * This executes on an IO-optimized thread pool, the function is main-safe.
     */
//    suspend fun fetchLatestNews(): MutableList<NetworkUserEvent> =
    // Move the execution to an IO-optimized thread since the ApiService
        // doesn't support coroutines and makes synchronous requests.
//        withContext(ioDispatcher) {
//            UserEventApiService.getEventUser()
//        }

}

//interface UserEventApiService {
//    @GET("users")
//    suspend fun getEventUser() : MutableList<NetworkUserEvent>
//}