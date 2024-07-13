package com.antonious.kotlincomposesample.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val loginRepository: LoginRepository
) : ViewModel() {

  init {
    Timber.d("injection LoginViewModel")
  }


}
