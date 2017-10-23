package se.springworks.mvvmcomponents.demo.trip

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip

class TripDetailsFragment(trip: Trip) : Fragment() {
  private lateinit var trip: Trip

  companion object {
    val TRIP_EXTRA = "trip"
  }

  init {
    val args = Bundle()
    args.putSerializable(TRIP_EXTRA, trip)
    setArguments(args)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    trip = arguments.getSerializable(TRIP_EXTRA) as? Trip ?: throw IllegalStateException("Trip should be provided")
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return super.onCreateView(inflater, container, savedInstanceState)
  }
}
