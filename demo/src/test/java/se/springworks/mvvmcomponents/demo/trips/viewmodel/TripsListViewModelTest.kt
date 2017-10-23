package se.springworks.mvvmcomponents.demo.trips.viewmodel

import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.ViewGroup
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import rx.Observable
import rx.schedulers.TestScheduler
import rx.subjects.BehaviorSubject
import se.springworks.mvvmcomponents.demo.core.SchedulesProvider
import se.springworks.mvvmcomponents.demo.core.TripFetcher
import se.springworks.mvvmcomponents.demo.trips.TripsAdapter
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip
import se.springworks.mvvmcomponents.recyclerview.adapter.BaseRecyclerViewAdapter
import se.springworks.mvvmcomponents.recyclerview.holder.ItemViewHolder

class TripsListViewModelTest {

  private lateinit var viewModel: TripsListViewModel

  private lateinit var tripFetcher: TripFetcher

  private lateinit var mockTrips: List<Trip>

  private lateinit var schedulersProvider: SchedulesProvider

  private lateinit var scheduler: TestScheduler

  private lateinit var activity: FragmentActivity

  private lateinit var onItemClickClosure: (trip: Trip) -> Unit

  private lateinit var adapter: TripsAdapter

  @Before
  fun setUp() {
    activity = mock<FragmentActivity>()
    mockTrips = listOf(Trip(1200, 420, "Kungsträdgårdsgatan", "Sveavägen 52"),
                       Trip(1900, 540, "Valhallavägen 12B", "Sveavägen 52"),
                       Trip(1300, 300, "Odenplan", "Sveavägen 52"))
    tripFetcher = mock {
      on { fetchTrips() } doReturn Observable.just(mockTrips)
    }
    scheduler = TestScheduler()
    schedulersProvider = mock {
      on { getUIScheduler() } doReturn scheduler
    }
    onItemClickClosure = mock()
    adapter = mock<TripsAdapter> {
      on { observeClickedItem() } doReturn BehaviorSubject.create<Trip>()
    }

    viewModel = TripsListViewModel(activity, tripFetcher, schedulersProvider, onItemClickClosure, adapter)
    viewModel.initialize()
  }

  @Test
  fun testResumeFetchDataAndDisplayProgress() {
    assertThat(viewModel.progressVisibility.get(), equalTo(View.GONE))
    assertThat(viewModel.tripsVisibility.get(), equalTo(View.GONE))
    assertThat(viewModel.errorVisibility.get(), equalTo(View.GONE))

    viewModel.resume()
    assertThat(viewModel.progressVisibility.get(), equalTo(View.VISIBLE))
    assertThat(viewModel.tripsVisibility.get(), equalTo(View.GONE))
    assertThat(viewModel.errorVisibility.get(), equalTo(View.GONE))
    verify(tripFetcher).fetchTrips()
  }

  @Test
  fun testHideProgressWhenReceiveData() {
    viewModel.gotTrips(mockTrips)
    assertThat(viewModel.progressVisibility.get(), equalTo(View.GONE))
    assertThat(viewModel.tripsVisibility.get(), equalTo(View.VISIBLE))
    assertThat(viewModel.errorVisibility.get(), equalTo(View.GONE))
    verify(adapter).setItems(mockTrips)
  }

  @Test
  fun testShowErrorWhenGotAnError() {
    val error = mock<Throwable>()
    viewModel.showError(error)
    assertThat(viewModel.progressVisibility.get(), equalTo(View.GONE))
    assertThat(viewModel.tripsVisibility.get(), equalTo(View.GONE))
    assertThat(viewModel.errorVisibility.get(), equalTo(View.VISIBLE))
  }

  @Test
  fun testRetryFetchNewTripsAndShowProgress() {
    val view = mock<View>()
    viewModel.onRetryClick(view)
    assertThat(viewModel.progressVisibility.get(), equalTo(View.VISIBLE))
    assertThat(viewModel.tripsVisibility.get(), equalTo(View.GONE))
    assertThat(viewModel.errorVisibility.get(), equalTo(View.GONE))
    verify(tripFetcher).fetchTrips()
  }
}
