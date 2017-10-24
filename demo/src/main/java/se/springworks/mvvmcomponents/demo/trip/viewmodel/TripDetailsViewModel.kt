package se.springworks.mvvmcomponents.demo.trip.viewmodel

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import rx.Observable
import se.springworks.mvvmcomponents.demo.core.DistanceFormatter
import se.springworks.mvvmcomponents.demo.core.TimeFormatter
import se.springworks.mvvmcomponents.demo.trip.model.TripDetailedModel
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip
import se.springworks.mvvmcomponents.viewmodel.ViewModelLifecycle
import java.util.concurrent.TimeUnit

class TripDetailsViewModel(private val timeFormatter: TimeFormatter,
                           private val distanceFormatter: DistanceFormatter,
                           private val trip: Trip) : ViewModelLifecycle {
  val title = ObservableField<String>()
  val distance = ObservableField<String>()
  val time = ObservableField<String>()

  val fetchButtonVisibility = ObservableInt()
  val additionalDataVisibility = ObservableInt()
  val progressVisibility = ObservableInt()
  val score = ObservableField<String>()
  val averageTime = ObservableField<String>()
  val rating = ObservableField<String>()

  override fun initialize() {
    title.set("${trip.addressFrom} ${trip.addressTo}")
    val formattedDistance = distanceFormatter.formatToMetresToKm(trip.distance)
    distance.set("Distance : $formattedDistance km")
    val formattedTime = timeFormatter.formatSecondsToMinutes(trip.time)
    time.set("Time : $formattedTime min")

    additionalDataVisibility.set(View.GONE)
    progressVisibility.set(View.GONE)
    fetchButtonVisibility.set(View.VISIBLE)
  }

  fun fetchMoreData(view: View) {
    fetchButtonVisibility.set(View.GONE)
    progressVisibility.set(View.VISIBLE)
    Observable.timer(1, TimeUnit.SECONDS)
        .map { TripDetailedModel("13 from 1345", trip.time.toString(), "98%") }
        .subscribe(this::gotDetailedData)
  }

  fun gotDetailedData(trip: TripDetailedModel) {
    progressVisibility.set(View.GONE)
    additionalDataVisibility.set(View.VISIBLE)
    score.set(trip.score)
    averageTime.set(trip.averageTime)
    rating.set(trip.rating)
  }

  override fun resume() {
  }

  override fun pause() {
  }

  override fun release() {
  }
}
