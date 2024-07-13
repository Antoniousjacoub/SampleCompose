package com.antonious.kotlincomposesample.ui.main

import androidx.annotation.WorkerThread
import com.antonious.kotlincomposesample.model.Poster
import com.antonious.kotlincomposesample.network.DisneyService
import com.antonious.kotlincomposesample.persistence.PosterDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class DashboardRepository @Inject constructor(
  private val disneyService: DisneyService,
  private val posterDao: PosterDao
) {

  init {
    Timber.d("Injection MainRepository")
  }

  @WorkerThread
  fun loadDisneyPosters(
    onStart: () -> Unit,
    onCompletion: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    val posters: List<Poster> = posterDao.getPosterList()
    if (posters.isEmpty()) {
      // request API network call asynchronously.
      disneyService.fetchDisneyPosterList()
        // handle the case when the API request gets a success response.
        .suspendOnSuccess {
          posterDao.insertPosterList(data)
          emit(data)
        }
        // handle the case when the API request is fails.
        // e.g. internal server error.
        .onFailure { onError(message()) }
    } else {
      emit(posters)
    }
  }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)


}
