# Weather app

An application for viewing the weather in specific locations. You can find a location by name or get
the current location of the phone. You can set reminders to view the weather or turn them off.
On the home screen you can see the last locations that have been viewed, and by long pressing on
location, you can switch to the mode of selecting and deleting locations.

On the settings screen, you can customize the weather units.

### Requirements
- Min SDK: 33
- JVM target: 17

### Screen structure

|- Home
|- Search
|- Settings

(Home or Search) -> Weather Graph

Weather graph:

|- Current weather
|- Forecast

### External libraries

- Koin
- Ktor
- Play services (Location)
- Google fonts
- Kotlin Datetime
- Accompanist
- Data store
- Room