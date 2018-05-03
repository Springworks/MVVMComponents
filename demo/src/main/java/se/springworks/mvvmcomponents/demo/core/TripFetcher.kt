package se.springworks.mvvmcomponents.demo.core

import io.reactivex.Observable
import se.springworks.mvvmcomponents.demo.trips.item.model.Trip
import java.util.concurrent.TimeUnit

open class TripFetcher(private val schedulesProvider: SchedulesProvider) {

  open fun fetchTrips(): Observable<List<Trip>> {
    return Observable.timer(3, TimeUnit.SECONDS)
        .map {
          listOf(
              Trip(1200, 420, "Kungsträdgårdsgatan", "Sveavägen 52"),
              Trip(1900, 540, "Valhallavägen 12B", "Sveavägen 52"),
              Trip(1300, 300, "Odenplan", "Sveavägen 52"),
              Trip(16000, 3420, "Farsta Strandplan", "Sveavägen 52"),
              Trip(24400, 5220, "Duvbo T-bana", "Farsta Strandplan"),
              Trip(7900, 1560, "Farsta", "Kasbyvägen"),
              Trip(6000, 1560, "Sankt Eriksgatan", "Duvbo T-bana")
          )
        }
        .subscribeOn(schedulesProvider.getIOScheduler())
  }
}
