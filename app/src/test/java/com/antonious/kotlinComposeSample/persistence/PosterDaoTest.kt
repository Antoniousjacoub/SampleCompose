package com.antonious.kotlinComposeSample.persistence

import com.antonious.kotlinComposeSample.MainCoroutinesRule
import com.antonious.kotlinComposeSample.utils.MockTestUtil.mockPosterList
import com.antonious.kotlincomposesample.model.Poster
import com.antonious.kotlincomposesample.persistence.PosterDao
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class PosterDaoTest : LocalDatabase() {

  private lateinit var posterDao: PosterDao

  @get:Rule
  val coroutinesRule = MainCoroutinesRule()

  @Before
  fun init() {
    posterDao = db.posterDao()
  }

  @Test
  fun insertAndLoadPosterListTest() = runTest {
    val mockDataList = mockPosterList()
    posterDao.insertPosterList(mockDataList)

    val loadFromDB = posterDao.getPosterList()
    assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

    val mockData = Poster.mock()
    assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
  }
}
