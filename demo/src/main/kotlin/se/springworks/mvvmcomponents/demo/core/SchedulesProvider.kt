package se.springworks.mvvmcomponents.demo.core

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class SchedulesProvider {
  open fun getUIScheduler(): Scheduler = AndroidSchedulers.mainThread()
  open fun getIOScheduler(): Scheduler = Schedulers.io()
  open fun getComputationScheduler(): Scheduler = Schedulers.computation()
  open fun getNewThreadScheduler(): Scheduler = Schedulers.newThread()
}
