package se.springworks.mvvmcomponents.recyclerview.viewmodel

import androidx.databinding.ObservableField

open class StringItemViewModel : ItemViewModel<String>() {
  val value = ObservableField<String>()

  override fun setItem(item: String, position: Int) {
    value.set(item)
  }
}
