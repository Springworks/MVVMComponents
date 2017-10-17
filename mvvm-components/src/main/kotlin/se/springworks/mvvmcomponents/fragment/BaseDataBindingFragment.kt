package se.springworks.mvvmcomponents.fragment

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.springworks.mvvmcomponents.viewmodel.ViewModelLifecycle

abstract class BaseDataBindingFragment<DataBinding : ViewDataBinding, ViewModel : ViewModelLifecycle> : Fragment() {

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
    viewModel = provideViewModel()
  }

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    return createView(inflater, container, savedInstanceState)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.initialize()
  }

  private fun createView(inflater: LayoutInflater,
                         container: ViewGroup?,
                         savedInstanceState: Bundle?): View {
    val binding = DataBindingUtil.inflate<DataBinding>(inflater, getLayoutId(), container, false)
    binding.setVariable(resourceBRViewModelId(), viewModel)
    return binding.root
  }

  override fun onResume() {
    super.onResume()
    viewModel.resume()
  }

  override fun onPause() {
    super.onPause()
    viewModel.pause()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    viewModel.release()
  }

}
