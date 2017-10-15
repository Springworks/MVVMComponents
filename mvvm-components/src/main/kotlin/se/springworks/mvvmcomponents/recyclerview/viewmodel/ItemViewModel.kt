package se.springworks.mvvmcomponents.recyclerview.viewmodel

import se.springworks.mvvmcomponents.viewmodel.ViewModelLifecycle

abstract class ItemViewModel<in Model : Any> : ViewModelLifecycle {

  abstract fun setItem(item: Model, position: Int)

  override fun initialize() {
  }

  override fun resume() {
    //do not override it, method `setItem` will be called after activity/fragment resume
    throw LifecycleException()
  }

  override fun pause() {
    //do not override it
    throw LifecycleException()
  }

  override fun release() {
  }
}

class LifecycleException : Throwable()
