package com.antonious.kotlincomposesample.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.antonious.kotlincomposesample.ui.main.DashboardScreen
import com.antonious.kotlincomposesample.ui.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

  @VisibleForTesting
  internal val viewModel: LoginViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MainTheme {
        DashboardScreen()
      }
    }
  }
}