package se.springworks.mvvmcomponents.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import se.springworks.mvvmcomponents.demo.trips.TripsListFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    supportFragmentManager.beginTransaction()
        .replace(R.id.main_fragment_container, TripsListFragment())
        .commit()
  }
}
