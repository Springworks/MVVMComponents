package se.springworks.mvvmcomponents.recyclerview.viewmodel

import android.databinding.ObservableField

class StringItemViewModel : ItemViewModel<String>() {
    val value = ObservableField<String>()

    override fun setItem(item: String, position: Int) {
        value.set(item)
    }
}
