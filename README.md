# Bluedot Takehome Project
This repository is for the submission of my take home project as part of Bluedot's recruitment process.
I was required to develop an Android application that would track the device location and report significant move events (i.e. if the user/device moved more than 10 metres from the previous location).

## How to run the project
### For advanced users/developers
1. You can clone this repository using the repository's link 'https://github.com/pafamable/bluedot-takehome' on your own Android Studio in your development machines. Alternatively, you can download the project as a ZIP file.
2. Once the project has been cloned or downloaded off of GitHub, build the project yourself from the ground up and have it run on your test emulators and/or test devices.
3. Run the application on your test emulators and/or test devices. When prompted, allow (or disallow to test the behaviour of the application when permissions are disallowed, it is up to you) the permissions needed to run the application.
4. Once the required permissions are allowed, the application will behave as intended. You can now test the functionalities of the application as described in the project overview.

### For novice/regular users
NOTE: You have to enable the installation of unknown apps on your device's settings. This setting can appear on different menus depending on your device manufacturer and/or Android version.
1. Download 'app-debug.apk' file on to your Android device.
2. Open the APK file. If you have enabled the installation of unknown apps on your device settings, the APK file will prompt you to install the application. Wait for the APK file to finish installing the application on your device. If you have not enable the installation of unknown apps on your device settings, the APK file will prompt you to enable the aformentioned setting before installing the application.
3. Once the application has been installed on your device, open the application. When prompted, allow (or disallow to test the behaviour of the application when permissions are disallowed, it is up to you) the permissions needed to run the application.
4. Once the required permissions are allowed, the application will behave as intended. You can now play around with the application.

## Implementation Details
### Design Pattern
The project has been developed using MVVM (Model-View-ViewModel) pattern. With this pattern, any changes to the data (in this case, Location data) will be reflected on the user interface in near real-time using LiveData. Also, ViewModel ensures that data survives during Android Lifecycle events. This can be observed during device screen orientation change, back and forth switching with other applications, going to the home screen then going back to the application, etc. These behaviours are handled automatically, without needing to invoke them through code.
### Functionality
The application uses Location Services provided by the Android Operating System. This is needed in order to track the location of the device. Once the location of the device has been determined, it will be shown an a Google Map view in the application. The application tracks the device's location every five to ten seconds. Once new location data has been received, the Google Map view will reflect the new location marker accordingly. If the distance between the current location and the previous location is greater than ten metres, a toast message will be shown indicating the distance between the two locations. During the preliminary testing of the application, this five to ten second window has been tested to allow for more distance to be covered by the application as compared to shorter windows. The application does not need any user interaction apart from the granting of the permissions needed by the application. Also, if the user puts the application on backgrund or exits the application altogether, the application stops tracking the device for privacy reasons.
### Bonus Features
Although it is not explicitly stated in the project overview, the application uses Google Map SDK in order to show the user's location on a Google Map view.
### Project Timeline
#### 26 February 2022 - 27 February 2022
Since I am not well-versed with Location Services on Android, I did my research regarding the usage of Location Services and Google Map SDK. This include reading official documentation and guides, blogs, GitHub repositories, a few Stack Overflow questions, and tutorials, both written and in video form. I also did test projects before doing the final version of the project.
#### 28 February 2022
I started developing the final project that I will be submitting for review. I finished the project in a matter of a few hours. I also conducted actual testing on an emulator (Pixel 5 running Android 12/API level 31) and a real Android device (Oneplus 6T running Android 11/API level 30), apart from the local unit testing. I must say I had fun working on the project. I also gained a few learnings here and there.

### References
#### Official Documentation
Receive location updates in Android with Kotlin -  
https://codelabs.developers.google.com/codelabs/while-in-use-location#0  
Build location-aware apps -  
https://developer.android.com/training/location  
Request location updates -  
https://developer.android.com/training/location/request-updates  
Change location settings -  
https://developer.android.com/training/location/change-location-settings  
Request app permissions -  
https://developer.android.com/training/permissions/requesting  
Location -  
https://developer.android.com/reference/android/location/Location  
Adding a map -  
https://developers.google.com/maps/documentation/android-sdk/map  
Class SphericalUtil -  
https://googlemaps.github.io/android-maps-utils/javadoc/com/google/maps/android/SphericalUtil.html  
#### Blogs
Geo-location tracking in Android with Kotlin -  
https://medium.com/@chris_42047/geo-location-tracking-in-android-with-kotlin-f4ec57743956  
Android Tutorial On Location Update With LiveData -  
https://proandroiddev.com/android-tutorial-on-location-update-with-livedata-774f8fcc9f15  
#### GitHub Repositories
While-in-use Location Codelab Repository -  
https://github.com/googlecodelabs/while-in-use-location  
My Plant Diary -  
https://github.com/discospiff/MyPlantDiaryQ  
#### Tutorials
Google Map - Part1: All you need to begin a google map app project with kotlin [ android tutorial ] -  
https://www.youtube.com/watch?v=t53B9UsNrAU  
Google Map - Part 2: Create MapView and get last location with kotlin [ android tutorial ] -  
https://www.youtube.com/watch?v=r6LhxNYuu-o  
Google Map - Part3: Request location updates every 2 seconds in foreground [ android tutorial ] -  
https://www.youtube.com/watch?v=ZkMre06lPaY&t=4s  
Google Map - Part4: update location in mapview like google map [ android tutorial ] -  
https://www.youtube.com/watch?v=lSL3lbRLZVc  
GPS with Live Data and MVVM in Android Q: Kotlin Code Demonstration -  
https://www.youtube.com/watch?v=VgZZemAwLTk&list=WL&index=327&t=1250s  
Create Google Map with MVVM Live Data in Fragment with Android Q and Kotlin -  
https://www.youtube.com/watch?v=ZAOfYRY8zJk&t=427s  
Google Maps Calculate Distance Between 2 Locations (Android Tutorials) -  
https://www.youtube.com/watch?v=86NAMO-MVIE  
Android JetPack Permissions is easy with Activityresultcontracts Kotlin -  
https://www.youtube.com/watch?v=kYY7IAcv8co  



