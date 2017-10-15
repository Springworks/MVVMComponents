package se.springworks.mvvmcomponents.viewmodel

interface ViewModelLifecycle {
    fun initialize()

    fun resume()

    fun pause()

    fun release()
}
