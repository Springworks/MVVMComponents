package se.springworks.mvvmcomponents.demo.trip

import android.annotation.SuppressLint
import android.os.Bundle
import se.springworks.mvvmcomponents.demo.BR
import se.springworks.mvvmcomponents.demo.R
import se.springworks.mvvmcomponents.demo.core.DistanceFormatter
import se.springworks.mvvmcomponents.demo.core.TimeFormatter
import se.springworks.mvvmcomponents.demo.databinding.TripDetailsFragmentBinding
import se.springworks.mvvmcomponents.demo.trip.viewmodel.TripDetailsViewModel
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip
import se.springworks.mvvmcomponents.fragment.BaseDataBindingFragment

@SuppressLint("ValidFragment")
class TripDetailsFragment(trip: Trip) : BaseDataBindingFragment<TripDetailsFragmentBinding, TripDetailsViewModel>() {
  private lateinit var trip: Trip
  private val timeFormatter = TimeFormatter()
  private val distanceFormatter = DistanceFormatter()

  companion object {

    val TRIP_EXTRA = "trip"
  }

  init {
    val args = Bundle()
    args.putSerializable(TRIP_EXTRA, trip)
    setArguments(args)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    trip = arguments?.getSerializable(TRIP_EXTRA) as? Trip ?: throw IllegalStateException("Trip should be provided")
    super.onCreate(savedInstanceState)
  }

  override fun resourceBRViewModelId(): Int = BR.viewModel

  override fun provideViewModel(): TripDetailsViewModel = TripDetailsViewModel(timeFormatter, distanceFormatter, trip)

  override fun getLayoutId(): Int = R.layout.trip_details_fragment
}
