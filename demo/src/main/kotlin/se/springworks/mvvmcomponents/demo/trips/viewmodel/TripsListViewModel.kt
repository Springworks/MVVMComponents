package se.springworks.mvvmcomponents.demo.trips.viewmodel

import androidx.databinding.ObservableInt
import androidx.fragment.app.FragmentActivity
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import se.springworks.mvvmcomponents.demo.core.SchedulesProvider
import se.springworks.mvvmcomponents.demo.core.TripFetcher
import se.springworks.mvvmcomponents.demo.trips.TripsAdapter
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip
import se.springworks.mvvmcomponents.recyclerview.adapter.ItemClickEvent
import se.springworks.mvvmcomponents.viewmodel.ViewModelLifecycle

class TripsListViewModel(private val activity: FragmentActivity,
                         private val tripFetcher: TripFetcher,
                         private val schedulesProvider: SchedulesProvider,
                         private val onTripClicked: (trip: ItemClickEvent<Trip>) -> Unit,
                         val adapter: TripsAdapter = TripsAdapter()) : ViewModelLifecycle {

  val tripsVisibility = ObservableInt()
  val progressVisibility = ObservableInt()
  val errorVisibility = ObservableInt()
  private val disposable: CompositeDisposable = CompositeDisposable()

  override fun initialize() {
    progressVisibility.set(View.GONE)
    tripsVisibility.set(View.GONE)
    errorVisibility.set(View.GONE)
  }

  override fun resume() {
    val itemClickSubscription = adapter.observeClickedItem()
        .subscribe(onTripClicked)
    disposable.add(itemClickSubscription)
    fetchTrips()
  }

  override fun pause() {
    disposable.clear()
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
    disposable.add(fetchSubscription)
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

