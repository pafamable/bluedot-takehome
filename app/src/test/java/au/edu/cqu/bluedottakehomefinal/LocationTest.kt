package au.edu.cqu.bluedottakehomefinal

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LocationTest {

    private val TEN_METERS = 10.0

    // this test case will check if calculateDistance() method works as intended
    @Test
    fun distanceChecker() {
        val latitude1 = -37.8190947
        val longitude1 = 144.9681381

        val latitude2 = -37.8190023
        val longitude2 = 144.9680933

        val position1 = LatLng(latitude1, longitude1)
        val position2 = LatLng(latitude2, longitude2)

        val distance = calculateDistance(position1, position2)
        val EXPECTED_DISTANCE = 11.00224582717868

        println("distanceChecker() Distance = $distance")

        assertEquals(EXPECTED_DISTANCE, distance)
    }

    // this test case will check if the distance between the two positions are greater than 10
    @Test
    fun distanceMoreThanTen() {
        val latitude1 = -37.8306428
        val longitude1 = 144.9648271

        val latitude2 = -37.8304542
        val longitude2 = 144.9646481

        val position1 = LatLng(latitude1, longitude1)
        val position2 = LatLng(latitude2, longitude2)

        val distance = calculateDistance(position1, position2)

        println("distanceMoreThanTen() Distance = $distance")

        assertTrue(distance >= TEN_METERS)
    }

    // this test case will check if the distance between the two positions are less than 10
    @Test
    fun distanceLessThanTen() {
        val latitude1 = -37.8323511
        val longitude1 = 144.9671713

        val latitude2 = -37.8323682
        val longitude2 = 144.9671101

        val position1 = LatLng(latitude1, longitude1)
        val position2 = LatLng(latitude2, longitude2)

        val distance = calculateDistance(position1, position2)

        println("distanceLessThanTen() Distance = $distance")

        assertTrue(distance < TEN_METERS)
    }

    // this test case will check if the two positions are equal to each other
    // (i.e. when the GPS position has not changed for a while)
    @Test
    fun locationNotChanged() {
        val latitude1 = -37.8321004
        val longitude1 = 144.9680319

        val latitude2 = -37.8321004
        val longitude2 = 144.9680319

        val position1 = LatLng(latitude1, longitude1)
        val position2 = LatLng(latitude2, longitude2)

        assertEquals(position1, position2)
    }

    // method for calculating the distance between two points on a map factoring in the Earth's radius
    private fun calculateDistance(previousPosition: LatLng, currentPosition: LatLng): Double
            = SphericalUtil.computeDistanceBetween(previousPosition, currentPosition)
}