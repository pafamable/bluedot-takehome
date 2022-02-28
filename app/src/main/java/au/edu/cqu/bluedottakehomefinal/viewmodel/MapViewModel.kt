package au.edu.cqu.bluedottakehomefinal.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import au.edu.cqu.bluedottakehomefinal.livedata.LocationLiveData

class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MapViewModel"

    private val locationLiveData = LocationLiveData(application)
    fun getLocationLiveData() = locationLiveData

    init {
        Log.d(TAG, "MapViewModel created")
    }
}