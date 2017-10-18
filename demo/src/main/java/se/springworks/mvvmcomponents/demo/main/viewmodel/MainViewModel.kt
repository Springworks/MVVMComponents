package se.springworks.mvvmcomponents.demo.main.viewmodel

import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import se.springworks.mvvmcomponents.demo.core.TripFetcher
import se.springworks.mvvmcomponents.demo.main.item.model.Trip
import se.springworks.mvvmcomponents.viewmodel.ViewModelLifecycle

class MainViewModel(private val tripFetcher: TripFetcher,
                    private val onDataLoaded: (trips: List<Trip>) -> Unit) : ViewModelLifecycle {

    private lateinit var subscription: Subscription

    override fun initialize() {

    }

    override fun resume() {
        subscription = tripFetcher.fetchTrips()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onDataLoaded(it) }
    }

    override fun pause() {
        subscription.unsubscribe()
    }

    override fun release() {

    }
}

