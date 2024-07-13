package com.antonious.kotlincomposesample.ui.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.antonious.kotlincomposesample.ui.dashboardui.Posters
import com.antonious.kotlincomposesample.ui.details.PosterDetails
import com.antonious.kotlincomposesample.ui.login.LoginForm
import com.antonious.kotlincomposesample.ui.navigtion.NavScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreen() {
  val navController = rememberNavController()

  val colors = MaterialTheme.colors
  val systemUiController = rememberSystemUiController()

  var statusBarColor by remember { mutableStateOf(colors.primaryVariant) }
  var navigationBarColor by remember { mutableStateOf(colors.primaryVariant) }

  val animatedStatusBarColor by animateColorAsState(
    targetValue = statusBarColor,
    animationSpec = tween()
  )
  val animatedNavigationBarColor by animateColorAsState(
    targetValue = navigationBarColor,
    animationSpec = tween()
  )

  NavHost(navController = navController, startDestination = NavScreen.Login.route) {
    composable(NavScreen.Login.route) {
      LoginForm(
        onLogin = {
          navController.navigate("${NavScreen.Home.route}/${it.userName}")
        }
      )

      LaunchedEffect(Unit) {
        statusBarColor = colors.primaryVariant
        navigationBarColor = colors.primaryVariant
      }
    }
    composable(
      route = NavScreen.Home.routeWithArgument,
      arguments = listOf(
        navArgument(NavScreen.Home.argument0) { type = NavType.StringType }
      )
    ) { backStackEntry ->
      val userName =
        backStackEntry.arguments?.getString(NavScreen.Home.argument0) ?: return@composable

      Posters(userName, viewModel = hiltViewModel()) {
        navController.navigate("${NavScreen.PosterDetails.route}/$it")
      }

      LaunchedEffect(Unit) {
        statusBarColor = Color.Transparent
        navigationBarColor = colors.background
      }
    }

    composable(
      route = NavScreen.PosterDetails.routeWithArgument,
      arguments = listOf(
        navArgument(NavScreen.PosterDetails.argument0) { type = NavType.LongType }
      )
    ) { backStackEntry ->
      val posterId =
        backStackEntry.arguments?.getLong(NavScreen.PosterDetails.argument0) ?: return@composable

      PosterDetails(posterId = posterId, viewModel = hiltViewModel()) {
        navController.navigateUp()
      }

      LaunchedEffect(Unit) {
        statusBarColor = Color.Transparent
        navigationBarColor = colors.background
      }
    }
  }

  LaunchedEffect(animatedStatusBarColor, animatedNavigationBarColor) {
    systemUiController.setStatusBarColor(animatedStatusBarColor)
    systemUiController.setNavigationBarColor(animatedNavigationBarColor)
  }
}


