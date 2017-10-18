package se.springworks.mvvmcomponents.demo

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import se.springworks.mvvmcomponents.activity.BaseDataBindingActivity
import se.springworks.mvvmcomponents.demo.core.TripFetcher
import se.springworks.mvvmcomponents.demo.databinding.ActivityMainBinding
import se.springworks.mvvmcomponents.demo.main.TripsAdapter
import se.springworks.mvvmcomponents.demo.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.main_recycler_view as recyclerView

class MainActivity : BaseDataBindingActivity<ActivityMainBinding, MainViewModel>() {

    private val tripFetcher = TripFetcher()

    private lateinit var adapter: TripsAdapter

    override fun resourceBRViewModelId(): Int = BR.viewModel

    override fun provideViewModel(): MainViewModel = MainViewModel(tripFetcher, {
        adapter.setItems(it)
    })

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TripsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }
}
