package se.springworks.mvvmcomponents.demo.main.viewmodel

import android.view.View
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import rx.Observable
import rx.schedulers.TestScheduler
import se.springworks.mvvmcomponents.demo.core.SchedulesProvider
import se.springworks.mvvmcomponents.demo.core.TripFetcher
import se.springworks.mvvmcomponents.demo.main.item.model.Trip

class MainViewModelTest {

  private lateinit var viewModel: MainViewModel

  private lateinit var tripFetcher: TripFetcher

  private lateinit var onDataLoaded: (trips: List<Trip>) -> Unit

  private lateinit var mockTrips: List<Trip>

  private lateinit var schedulersProvider: SchedulesProvider

  private lateinit var scheduler: TestScheduler

  @Before
  fun setUp() {
    mockTrips = listOf(Trip(1200, 420, "Kungsträdgårdsgatan", "Sveavägen 52"),
                       Trip(1900, 540, "Valhallavägen 12B", "Sveavägen 52"),
                       Trip(1300, 300, "Odenplan", "Sveavägen 52"))
    tripFetcher = mock {
      on { fetchTrips() } doReturn Observable.just(mockTrips)
    }
    onDataLoaded = mock()
    scheduler = TestScheduler()
    schedulersProvider = mock {
      on { getUIScheduler() } doReturn scheduler
    }
    viewModel = MainViewModel(tripFetcher, schedulersProvider, onDataLoaded)
    viewModel.initialize()
  }

  @Test
  fun testResumeFetchDataAndDisplayProgress() {
    assertThat(viewModel.progressVisibility.get(), equalTo(View.GONE))
    assertThat(viewModel.tripsVisibility.get(), equalTo(View.GONE))

    viewModel.resume()
    assertThat(viewModel.progressVisibility.get(), equalTo(View.VISIBLE))
    assertThat(viewModel.tripsVisibility.get(), equalTo(View.GONE))
    verify(tripFetcher).fetchTrips()
  }

  @Test
  fun testHideProgressWhenReceiveData(){
    viewModel.gotTrips(mockTrips)
    assertThat(viewModel.progressVisibility.get(), equalTo(View.GONE))
    assertThat(viewModel.tripsVisibility.get(), equalTo(View.VISIBLE))
  }
}
