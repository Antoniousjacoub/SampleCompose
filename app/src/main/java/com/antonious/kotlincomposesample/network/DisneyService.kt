package com.antonious.kotlincomposesample.network

import com.antonious.kotlincomposesample.model.Poster
import com.antonious.kotlincomposesample.utils.Constants
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface DisneyService {

  @GET(Constants.APIServicesURLs.API_DISNEY_LIST)
  suspend fun fetchDisneyPosterList(): ApiResponse<List<Poster>>
}
