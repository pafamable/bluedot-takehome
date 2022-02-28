package au.edu.cqu.bluedottakehomefinal.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import au.edu.cqu.bluedottakehomefinal.R
import au.edu.cqu.bluedottakehomefinal.databinding.MapFragmentBinding
import au.edu.cqu.bluedottakehomefinal.viewmodel.MapViewModel
import au.edu.cqu.bluedottakehomefinal.viewmodel.MapViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.SphericalUtil

class MapFragment : Fragment() {

    private val TAG = "MapFragment"

    private lateinit var binding: MapFragmentBinding

    private lateinit var viewModel: MapViewModel
    private lateinit var viewModelFactory: MapViewModelFactory

    private val permissionRequests = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreateView called")

        binding = MapFragmentBinding.inflate(inflater, container, false)

        viewModelFactory = MapViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory)[MapViewModel::class.java]

        val mapFragment =
            childFragmentManager.findFragmentById((R.id.mapview)) as SupportMapFragment

        mapFragment.getMapAsync {
            map = it
        }

        checkForLocationPermissions()

        return binding.root
    }

    private fun checkForLocationPermissions() {
        when {
            // if the application has the permissions to access GPS location data,
            // the application will proceed as intended behaviour
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                requestLocationUpdates()
            }

            // if the application has the permissions to access GPS location data,
            // a toast message will be shown citing the reasoning for the permission requests
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                Toast.makeText(
                    context,
                    "Location permissions are needed to access GPS data",
                    Toast.LENGTH_LONG
                ).show()
            }

            // if this is the first time that the application has been executed,
            // the application will ask the user to grant/reject permissions
            else -> {
                requestLocationPermissions.launch(permissionRequests)
            }
        }
    }

    private val requestLocationPermissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        // if both permissions are granted, the application will proceed as intended behaviour
        if (it[Manifest.permission.ACCESS_FINE_LOCATION] == true && it[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            Log.d(TAG, "Location permissions were granted")
            requestLocationUpdates()
        }

        // if both permissions are NOT granted
        else {
            Log.d(TAG, "Location permissions were NOT granted")
            Toast.makeText(
                context,
                "Unable to update location without permission",
                Toast.LENGTH_LONG
            ).show()
        }
    }


    private fun requestLocationUpdates() {
        Log.d(TAG, "requestLocationUpdates is called")
        var previousPosition = LatLng(0.0, 0.0)

        viewModel.getLocationLiveData().observe(viewLifecycleOwner, Observer {

            val currentPosition = LatLng(it.latitude, it.longitude)

            // if the previous location has not been set yet (i.e. when the app has been run for the first time or after a while)
            // the currentPosition would be set as the previousPosition
            if (previousPosition == LatLng(0.0, 0.0)) {
                previousPosition = currentPosition
            }

            Log.d(
                TAG,
                "Previous Position = Latitude: ${previousPosition.latitude}, Longitude: ${previousPosition.longitude}"
            )

            // update the map view with the current location
            map.clear()
            map.addMarker(MarkerOptions().position(currentPosition).title("Current Position"))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 18F))

            // calculate the distance between the two sets of location coordinates
            val distance = calculateDistance(previousPosition, currentPosition)

            val TEN_METERS = 10.0

            if (distance >= TEN_METERS) {
                Toast.makeText(
                    requireActivity(),
                    "You have moved ${
                        String.format(
                            "%.2f",
                            distance
                        )
                    } metres from your previous location",
                    Toast.LENGTH_SHORT
                ).show()
            }

            Log.d(
                TAG,
                "Current Position = Latitude: ${currentPosition.latitude}, Longitude: ${currentPosition.longitude}, Distance = ${
                    String.format(
                        "%.2f",
                        distance
                    )
                } metres"
            )

            previousPosition = currentPosition
        })
    }

    // method for calculating the distance between two points on a map factoring the Earth's radius
    private fun calculateDistance(previousPosition: LatLng, currentPosition: LatLng): Double =
        SphericalUtil.computeDistanceBetween(previousPosition, currentPosition)
}