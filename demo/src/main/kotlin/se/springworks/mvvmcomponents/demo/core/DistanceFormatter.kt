package se.springworks.mvvmcomponents.demo.core

open class DistanceFormatter {
  open fun formatToMetresToKm(distanceInMetres: Int): Float = distanceInMetres / 1000f
}
