package se.springworks.mvvmcomponents.demo

import se.springworks.mvvmcomponents.activity.BaseDataBindingActivity
import se.springworks.mvvmcomponents.demo.databinding.ActivityMainBinding
import se.springworks.mvvmcomponents.demo.main.viewmodel.MainViewModel

class MainActivity : BaseDataBindingActivity<ActivityMainBinding, MainViewModel>() {

  override fun resourceBRViewModelId(): Int {
    return BR.viewModel
  }

  override fun provideViewModel(): MainViewModel {
    return MainViewModel()
  }

  override fun getLayoutId(): Int {
    return R.layout.activity_main
  }
}
