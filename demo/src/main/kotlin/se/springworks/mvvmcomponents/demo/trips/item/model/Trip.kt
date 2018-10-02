package se.springworks.mvvmcomponents.demo.trips.item.model

import java.io.Serializable

data class Trip(val distance: Int, val time: Int, val addressFrom: String, val addressTo: String) : Serializable
