package se.springworks.mvvmcomponents.demo.core

class DistanceFormatter {
  fun formatToMetresToKm(distanceInMetres: Int): Float = distanceInMetres / 1000f
}
