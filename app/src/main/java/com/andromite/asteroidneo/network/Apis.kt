package com.andromite.asteroidneo.network

import com.andromite.asteroidneo.network.models.NeoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Apis {

    @GET("neo/rest/v1/feed")
    suspend fun getNeoResponse(
        @Query("start_date") startDate : String,
        @Query("end_date") endDate : String,
        @Query("api_key") apikey : String,
    ) : Response<NeoResponse>

//    @GET("list_movies.json")
//    suspend fun getMoviesList(
//        @Query("page") page: Int? = 1,
//        @Query("limit") limit: Int? = Constants.pageLimit,
//        @Query("genre") genre: String = "All",
//        @Query("sort_by") sort_by: String = "date_added",
//        @Query("order_by") order_by: String = "asc",
//        @Query("quality") quality: String = "All"
//    ): Response<MoviesList>


}