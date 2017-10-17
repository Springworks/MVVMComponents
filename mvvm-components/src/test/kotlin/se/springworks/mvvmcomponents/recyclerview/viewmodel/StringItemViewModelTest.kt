package se.springworks.mvvmcomponents.recyclerview.viewmodel

import com.nhaarman.mockito_kotlin.isNull
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class StringItemViewModelTest {
  private lateinit var viewModel: StringItemViewModel
  @Before
  fun setUp() {
    viewModel = StringItemViewModel()
  }

  @Test
  fun testGetValueBeforeAndAfterSet() {
    assertThat(viewModel.value.get(), CoreMatchers.nullValue())
    viewModel.setItem("123", 0)
    assertThat(viewModel.value.get(), CoreMatchers.equalTo("123"))
  }

}
