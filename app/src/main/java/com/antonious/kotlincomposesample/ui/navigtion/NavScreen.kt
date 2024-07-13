package com.antonious.kotlincomposesample.ui.navigtion

sealed class NavScreen(val route: String) {

  object Login : NavScreen("Login")

  object Home : NavScreen("Home") {

    const val routeWithArgument: String = "Home/{userName}"

    const val argument0: String = "userName"
  }

  object PosterDetails : NavScreen("PosterDetails") {

    const val routeWithArgument: String = "PosterDetails/{posterId}"

    const val argument0: String = "posterId"
  }
}