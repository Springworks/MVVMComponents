package se.springworks.mvvmcomponents.demo.trips

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import se.springworks.mvvmcomponents.demo.BR
import se.springworks.mvvmcomponents.demo.R
import se.springworks.mvvmcomponents.demo.core.SchedulesProvider
import se.springworks.mvvmcomponents.demo.core.TripFetcher
import se.springworks.mvvmcomponents.demo.databinding.TripsListFragmentBinding
import se.springworks.mvvmcomponents.demo.trip.TripDetailsFragment
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip
import se.springworks.mvvmcomponents.demo.trips.viewmodel.TripsListViewModel
import se.springworks.mvvmcomponents.fragment.BaseDataBindingFragment
import kotlinx.android.synthetic.main.trips_list_fragment.main_recycler_view as recyclerView

class TripsListFragment : BaseDataBindingFragment<TripsListFragmentBinding, TripsListViewModel>() {

  private val schedulersProvider = SchedulesProvider()
  private val tripFetcher = TripFetcher(schedulersProvider)

  private lateinit var adapter: TripsAdapter

  override fun resourceBRViewModelId(): Int = BR.viewModel

  override fun provideViewModel(): TripsListViewModel
      = TripsListViewModel(activity,
                           tripFetcher,
                           schedulersProvider,
                           this::openTripDetails)

  override fun getLayoutId(): Int = R.layout.trips_list_fragment

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    adapter = viewModel.adapter
    recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    recyclerView.adapter = adapter
  }

  private fun openTripDetails(trip: Trip) {
    activity.supportFragmentManager
        .beginTransaction()
        .add(R.id.main_fragment_container, TripDetailsFragment(trip), TripDetailsFragment::class.java.simpleName)
        .addToBackStack(TripDetailsFragment::class.java.simpleName)
        .commit()
  }
}
