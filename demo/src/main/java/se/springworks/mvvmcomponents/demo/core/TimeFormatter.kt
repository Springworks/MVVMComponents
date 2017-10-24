package se.springworks.mvvmcomponents.demo.core

open class TimeFormatter {
  open fun formatSecondsToMinutes(seconds: Int): Float = seconds / 60f
}
