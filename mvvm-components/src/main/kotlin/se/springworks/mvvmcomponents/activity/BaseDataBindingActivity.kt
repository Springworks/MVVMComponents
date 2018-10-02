package se.springworks.mvvmcomponents.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import se.springworks.mvvmcomponents.viewmodel.ViewModelLifecycle

abstract class BaseDataBindingActivity<DataBinding : ViewDataBinding, ViewModel : ViewModelLifecycle> : AppCompatActivity() {
  lateinit var viewModel: ViewModel

  /*
  looks a bit ugly, but i found only this way to provide actual resource id which should be bounded
  You should provide ${yourPackageName.BR.viewModel here}
   */
  protected abstract fun resourceBRViewModelId(): Int

  protected abstract fun provideViewModel(): ViewModel

  protected abstract fun getLayoutId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView()
    viewModel.initialize()
  }

  private fun setContentView() {
    val layoutId = getLayoutId()
    if (layoutId > 0) {
      setContentView(layoutId)
    }
    viewModel = provideViewModel()
    setDatabindingContentView(getLayoutId())
  }

  /*
  One of the strong requirements here, inside XML view model should always be named as 'viewModel' to conform protocol
   */
  private fun setDatabindingContentView(layoutId: Int) {
    val binding = DataBindingUtil.setContentView<DataBinding>(this, layoutId)
    binding.setVariable(resourceBRViewModelId(), viewModel)
  }

  override fun onResume() {
    super.onResume()
    viewModel.resume()
  }

  override fun onPause() {
    super.onPause()
    viewModel.pause()
  }

  override fun onDestroy() {
    super.onDestroy()
    viewModel.release()
  }
}
