package se.springworks.mvvmcomponents.demo.trips

import android.view.ViewGroup
import se.springworks.mvvmcomponents.demo.core.DistanceFormatter
import se.springworks.mvvmcomponents.demo.core.TimeFormatter
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip
import se.springworks.mvvmcomponents.demo.trips.item.viewmodel.TripItemViewHolder
import se.springworks.mvvmcomponents.recyclerview.adapter.BaseRecyclerViewAdapter
import se.springworks.mvvmcomponents.recyclerview.holder.ItemViewHolder

open class TripsAdapter : BaseRecyclerViewAdapter<Trip>() {
  private val distanceFormatter = DistanceFormatter()
  private val timeFormatter = TimeFormatter()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<Trip> =
      TripItemViewHolder(parent, distanceFormatter, timeFormatter)
}
