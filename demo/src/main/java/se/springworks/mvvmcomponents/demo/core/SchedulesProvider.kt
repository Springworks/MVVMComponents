package se.springworks.mvvmcomponents.demo.core

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

open class SchedulesProvider {
  open fun getUIScheduler(): Scheduler = AndroidSchedulers.mainThread()
  open fun getIOScheduler(): Scheduler = Schedulers.io()
  open fun getComputationScheduler(): Scheduler = Schedulers.computation()
  open fun getNewThreadScheduler(): Scheduler = Schedulers.newThread()
}
