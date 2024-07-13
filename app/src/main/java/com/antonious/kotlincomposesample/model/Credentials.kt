package com.antonious.kotlincomposesample.model

data class Credentials(
  var userName: String = "",
  var pwd: String = "",
  var remember: Boolean = false
) {
  fun isNotEmpty(): Boolean {
    return userName.isNotEmpty() && pwd.isNotEmpty()
  }
}