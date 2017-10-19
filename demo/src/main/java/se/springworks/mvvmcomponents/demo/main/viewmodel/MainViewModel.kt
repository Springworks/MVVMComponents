package se.springworks.mvvmcomponents.demo.main.viewmodel

import android.databinding.ObservableInt
import android.view.View
import rx.Subscription
import se.springworks.mvvmcomponents.demo.core.SchedulesProvider
import se.springworks.mvvmcomponents.demo.core.TripFetcher
import se.springworks.mvvmcomponents.demo.main.item.model.Trip
import se.springworks.mvvmcomponents.viewmodel.ViewModelLifecycle

class MainViewModel(private val tripFetcher: TripFetcher,
                    private val schedulesProvider: SchedulesProvider,
                    private val onDataLoaded: (trips: List<Trip>) -> Unit) : ViewModelLifecycle {

  val tripsVisibility = ObservableInt()
  val progressVisibility = ObservableInt()
  private lateinit var subscription: Subscription

  override fun initialize() {
    progressVisibility.set(View.GONE)
    tripsVisibility.set(View.GONE)
  }

  override fun resume() {
    showProgress()
    subscription = tripFetcher.fetchTrips()
        .observeOn(schedulesProvider.getUIScheduler())
        .subscribe(this::gotTrips)
  }

  override fun pause() {
    subscription.unsubscribe()
  }

  override fun release() {

  }

  fun gotTrips(trips: List<Trip>) {
    hideProgress()
    onDataLoaded(trips)
  }

  private fun showProgress() {
    progressVisibility.set(View.VISIBLE)
    tripsVisibility.set(View.GONE)
  }

  private fun hideProgress() {
    progressVisibility.set(View.GONE)
    tripsVisibility.set(View.VISIBLE)
  }
}

