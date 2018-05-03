package se.springworks.mvvmcomponents.demo.trips.viewmodel

import android.databinding.ObservableInt
import android.support.v4.app.FragmentActivity
import android.view.View
import rx.subscriptions.CompositeSubscription
import se.springworks.mvvmcomponents.demo.core.SchedulesProvider
import se.springworks.mvvmcomponents.demo.core.TripFetcher
import se.springworks.mvvmcomponents.demo.trips.TripsAdapter
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip
import se.springworks.mvvmcomponents.viewmodel.ViewModelLifecycle

class TripsListViewModel(private val activity: FragmentActivity,
                         private val tripFetcher: TripFetcher,
                         private val schedulesProvider: SchedulesProvider,
                         private val onTripClicked: (trip: Trip) -> Unit,
                         val adapter: TripsAdapter = TripsAdapter()) : ViewModelLifecycle {

  val tripsVisibility = ObservableInt()
  val progressVisibility = ObservableInt()
  val errorVisibility = ObservableInt()
  private lateinit var subscription: CompositeSubscription

  override fun initialize() {
    progressVisibility.set(View.GONE)
    tripsVisibility.set(View.GONE)
    errorVisibility.set(View.GONE)
  }

  override fun resume() {
    subscription = CompositeSubscription()
    val itemClickSubscription = adapter.observeClickedItem()
        .subscribe(onTripClicked)
    subscription.add(itemClickSubscription)
    fetchTrips()
  }

  override fun pause() {
    subscription.unsubscribe()
  }

  override fun release() {

  }

  fun onRetryClick(view: View) {
    fetchTrips()
  }

  fun fetchTrips() {
    showProgress()
    val fetchSubscription = tripFetcher.fetchTrips()
        .observeOn(schedulesProvider.getUIScheduler())
        .subscribe(this::gotTrips, this::showError)
    subscription.add(fetchSubscription)
  }

  fun gotTrips(trips: List<Trip>) {
    hideProgress()
    tripsVisibility.set(View.VISIBLE)
    errorVisibility.set(View.GONE)
    adapter.setItems(trips)
  }

  fun showError(error: Throwable) {
    hideProgress()
    tripsVisibility.set(View.GONE)
    errorVisibility.set(View.VISIBLE)
  }

  private fun showProgress() {
    progressVisibility.set(View.VISIBLE)
    tripsVisibility.set(View.GONE)
    errorVisibility.set(View.GONE)
  }

  private fun hideProgress() {
    progressVisibility.set(View.GONE)
  }
}

