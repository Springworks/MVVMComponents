package se.springworks.mvvmcomponents.recyclerview.viewmodel

import org.junit.Before
import org.junit.Test

class EmptyItemViewModelTest {

  private lateinit var viewModel: EmptyItemViewModel

  @Before
  fun setUp() {
    viewModel = EmptyItemViewModel()
  }

  @Test(expected = LifecycleException::class)
  fun testResumeThrowError() {
    viewModel.resume()
  }

  @Test(expected = LifecycleException::class)
  fun testPauseThrowError() {
    viewModel.pause()
  }

}
