package com.antonious.kotlincomposesample.ui.login

import timber.log.Timber
import javax.inject.Inject

class LoginRepository @Inject constructor(
) {

  init {
    Timber.d("Injection MainRepository")
  }


}
