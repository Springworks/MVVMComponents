package se.springworks.mvvmcomponents.demo.trips.item.viewmodel

import android.databinding.ObservableField
import android.view.LayoutInflater
import android.view.ViewGroup
import se.springworks.mvvmcomponents.demo.BR
import se.springworks.mvvmcomponents.demo.core.DistanceFormatter
import se.springworks.mvvmcomponents.demo.core.TimeFormatter
import se.springworks.mvvmcomponents.demo.databinding.TripItemBinding
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip
import se.springworks.mvvmcomponents.recyclerview.holder.ItemViewHolder
import se.springworks.mvvmcomponents.recyclerview.viewmodel.ItemViewModel

class TripItemViewHolder(parent: ViewGroup,
                         distanceFormatter: DistanceFormatter,
                         timeFormatter: TimeFormatter)
  : ItemViewHolder<Trip>(parent,
                         LayoutInflater.from(parent.context),
                         TripItemBinding::inflate,
                         TripItemViewModel(distanceFormatter, timeFormatter)) {
  override fun getViewModelResID(): Int = BR.viewModel
}

class TripItemViewModel(private val distanceFormatter: DistanceFormatter,
                        private val timeFormatter: TimeFormatter) : ItemViewModel<Trip>() {

  val distance = ObservableField<String>()
  val time = ObservableField<String>()
  val address = ObservableField<String>()

  override fun setItem(item: Trip, position: Int) {
    val distanceInKm = distanceFormatter.formatToMetresToKm(item.distance)
    distance.set("Distance: $distanceInKm km")
    val timeInMinutes = timeFormatter.formatSecondsToMinutes(item.time)
    time.set("Time: $timeInMinutes min")
    address.set("${item.addressFrom} ${item.addressTo}")
  }
}
