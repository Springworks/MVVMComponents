package se.springworks.mvvmcomponents.demo.trip.viewmodel

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import se.springworks.mvvmcomponents.demo.core.DistanceFormatter
import se.springworks.mvvmcomponents.demo.core.TimeFormatter
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip

class TripDetailsViewModelTest {

  private lateinit var viewModel: TripDetailsViewModel

  @Before
  fun setUp() {
    val timeFormatter = mock<TimeFormatter> {
      on { formatSecondsToMinutes(123) } doReturn 123f
    }
    val distanceFormatter = mock<DistanceFormatter> {
      on { formatToMetresToKm(1234) } doReturn 1234f
    }
    val trip = Trip(1234, 123, "From", "To")
    viewModel = TripDetailsViewModel(timeFormatter, distanceFormatter, trip)
    viewModel.initialize()
  }

  @Test
  fun testInitializeInitialValues() {
    MatcherAssert.assertThat(viewModel.title.get(), equalTo("From To"))
  }
}
