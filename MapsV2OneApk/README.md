HERE API sample: One APK Maps v2 sample
==========================================

This Nokia sample illustrates how to implement Google Maps v2 and HERE Maps
offering in a single APK, and how to select the maps implementation to be used
run-time. This sample is especially useful should you already have an
application using either Google Maps v2 in terms of demonstrating how to
add HERE Maps alongside your original implementation.

For more information see Nokia X Developer's Library:
http://developer.nokia.com/resources/library/nokia-x/here-maps/one-apk-with-here-maps-and-google-maps.html


Instructions
--------------------------------------------------------------------------------

Import the project to your IDE. The recommended build target for the project is
Google APIs/API level 16. 

This sample application requires Support library, Google Service library project
and HERE reference library for Google Maps Android API v2 to be added to the
build path. HERE reference library for Google Maps Android API v2 is hosted in a
separate repository and added to this repository as a submodule.

### Building with Gradle ###

This sample can be compiled and installed to attached device with the included 
Gradle wrapper by executing:

**On Linux or OS X:**

```
./gradlew clean installDebug
```

**On Windows:**

```
gradlew.bat clean installDebug
```

Gradle resolves Google Play services and Support library dependencies using the
local repositories. Make sure you have installed them using the SDK manager.
Launch Android SDK manager and make sure you have checked **Android Support
Repository** and **Google Repository** checkboxes from the **Extras** section.

### Building with Eclipse ###

To build and run the sample application with Eclipse, follow these steps:

1. Import the sample application into Eclipse by clicking **File > Import**,
   then selecting **Existing Android Code into Workspace**.
2. Modify the manifest file and change HERE `app_id` and `app_token` values to
   valid keys.
3. Modify the manifest file and add Google Maps Android API v2 keys. (Needed
   only when trying the sample in a device with Google Maps.)
4. In the same manner, import the **Google Play Services library** and
   **Google Play Services reference library** into Eclipse. (See the 
   [Android Developers website](http://developer.android.com/google/play-services/setup.html)
   for more details on the Google Play Services library project.)
5. Right-click on the sample project, select **Android Tools > Add Support
   Library**
6. Right-click on the sample project, open the **Properties** dialog, and select
   the **Android** option from the left column. Ensure that the Google Play
   Services library and
   [HERE reference library](https://github.com/nokia-developer/nokia-x-here-maps-wrapper-v2)
   is added to the project as a Project Library.
7. From the same **Properties** dialog, ensure that the project build target is
   set to **Google APIs**.
8. Next, select the **Java Build Path** option from the left column. Select the
   **Libraries** tab and use the **Add External JARs** button to add
   `com.here.android.sdk.jar` into the project.
9. Click on the **Order and Export** tab and make sure that
   `com.here.android.sdk.jar` is deselected.
10. Click **OK** to close the Properties dialog. Select **Run > Run** from the
    menu to run the project.


Known issues
--------------------------------------------------------------------------------

None.


License
--------------------------------------------------------------------------------

See the separate license file provided with this project.
