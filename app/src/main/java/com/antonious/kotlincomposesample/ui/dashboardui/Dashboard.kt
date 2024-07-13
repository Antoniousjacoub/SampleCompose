package com.antonious.kotlincomposesample.ui.dashboardui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.antonious.kotlincomposesample.R
import com.antonious.kotlincomposesample.model.Poster
import com.antonious.kotlincomposesample.ui.main.DashboardViewModel
import com.antonious.kotlincomposesample.ui.theme.purple200

@Composable
fun Posters(
  userName: String,
  viewModel: DashboardViewModel,
  selectPoster: (Long) -> Unit
) {
  val posters: List<Poster> by viewModel.posterList.collectAsState(initial = listOf())
  val isLoading: Boolean by viewModel.isLoading

  ConstraintLayout {
    val (body, progress) = createRefs()
    Scaffold(
      backgroundColor = MaterialTheme.colors.primarySurface,
      topBar = { PosterAppBar() },
      modifier = Modifier.constrainAs(body) {
        top.linkTo(parent.top)
      }
    ) { innerPadding ->
      val modifier = Modifier.padding(innerPadding)
      DashboardItem(userName, modifier, posters, selectPoster)
    }
    if (isLoading) {
      CircularProgressIndicator(
        modifier = Modifier
          .constrainAs(progress) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
          }
      )
    }
  }
}

@Preview
@Composable
private fun PosterAppBar() {
  TopAppBar(
    elevation = 6.dp,
    backgroundColor = purple200,
    modifier = Modifier
      .statusBarsPadding()
      .height(58.dp)
  ) {
    Text(
      modifier = Modifier
        .padding(8.dp)
        .align(Alignment.CenterVertically),
      text = stringResource(R.string.app_name),
      color = Color.White,
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold
    )
  }
}


