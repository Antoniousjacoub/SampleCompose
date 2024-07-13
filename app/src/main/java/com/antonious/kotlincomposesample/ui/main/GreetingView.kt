package com.antonious.kotlincomposesample.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar


@Preview
@Composable
fun GreetingPreview() {
  Greeting("Antonious")
}

@Composable
fun Greeting(userName: String) {
  val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

  Box(
    Modifier
      .fillMaxSize()
      .systemBarsPadding()
      .padding(10.dp)
  ) {
    Column {
      Text(text = getPeriod(hour))
      Text(text = userName)
      Divider(
        color = Color.Red, thickness = 1.dp,
        modifier = Modifier.padding(top = 20.dp)
      )
    }


  }
}

private fun getPeriod(hour: Int) = when (hour) {
  in 6..11 -> {
    "Good Morning"
  }

  in 12..16 -> {
    "Good Afternoon"
  }

  in 17..20 -> {
    "Good Evening"
  }

  in 21..23 -> {
    "Good Night"
  }

  else -> {
    "Good Morning"
  }
}