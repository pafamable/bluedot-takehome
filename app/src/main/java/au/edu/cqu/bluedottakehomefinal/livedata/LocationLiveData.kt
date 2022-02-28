package au.edu.cqu.bluedottakehomefinal.livedata

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import au.edu.cqu.bluedottakehomefinal.model.LocationCoordinates
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationLiveData(context: Context) : LiveData<LocationCoordinates>() {

    private val TAG = "LocationLiveData"

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            locationResult ?: return

            for (location in locationResult.locations) {

                Log.d(
                    TAG,
                    "Current Latitude: ${location.latitude}, Longitude: ${location.longitude}"
                )

                setLocationData(location)
            }
        }
    }

    private fun setLocationData(location: Location) {
        if (location != null) {
            value = LocationCoordinates(location.latitude, location.longitude)
        }
    }

    override fun onInactive() {
        super.onInactive()
        Log.d(TAG, "onInactive is called")
        // turn off location updates
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        Log.d(TAG, "onActive is called")
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.also {
                    setLocationData(it)
                }
            }
        startLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    companion object {
        private const val TEN_SECONDS: Long = 10000
        private const val FIVE_SECONDS: Long = 5000

        val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = TEN_SECONDS
            fastestInterval = FIVE_SECONDS
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
}