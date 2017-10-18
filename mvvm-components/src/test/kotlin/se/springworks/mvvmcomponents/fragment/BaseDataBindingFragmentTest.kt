package se.springworks.mvvmcomponents.fragment

import android.databinding.ViewDataBinding
import android.view.View
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import se.springworks.mvvmcomponents.recyclerview.viewmodel.StringItemViewModel

class StubBaseDataBindingFragment : BaseDataBindingFragment<ViewDataBinding, StringItemViewModel>() {
  override fun resourceBRViewModelId(): Int {
    return 0
  }

  override fun provideViewModel(): StringItemViewModel {
    return mock<StringItemViewModel>()
  }

  override fun getLayoutId(): Int {
    return 0
  }
}

class BaseDataBindingFragmentTest {
  private lateinit var fragment: StubBaseDataBindingFragment
  private lateinit var viewModelMock: StringItemViewModel

  @Before
  fun setUp() {
    fragment = StubBaseDataBindingFragment()
    fragment.onCreate(null)
    viewModelMock = fragment.viewModel
  }

  @Test
  fun testOnCreateInitViewModel() {
    MatcherAssert.assertThat(fragment.viewModel, CoreMatchers.notNullValue())
  }

  @Test
  fun testOnViewCreatedInitializeViewModel() {
    fragment.onViewCreated(mock<View>(), null)
    verify(viewModelMock).initialize()
  }

  @Test
  fun testOnResumeResumingViewModel() {
    fragment.onResume()
    verify(viewModelMock).resume()
  }

  @Test
  fun testOnPausePausingViewModel() {
    fragment.onPause()
    verify(viewModelMock).pause()
  }

  @Test
  fun testOnDestroyViewReleasingViewModel() {
    fragment.onDestroyView()
    verify(viewModelMock).release()
  }

}
