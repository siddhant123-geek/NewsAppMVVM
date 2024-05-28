# NewsAppMVVM
This is my News Application project, where I have used the modern  best practices  and framework/libraries offered by Android Jetpack.


# Architecture used is MVVM(Model View View Model)
![MVVMImage](https://github.com/siddhant123-geek/MyNewsApplicationProjectWithJetpackComposeAndDaggerHilt/assets/82453362/439310a1-bc05-4d6f-b6e1-fcf116b81eb2)


# Final project uses(Major libraries/frameworks)
- **Dagger Hilt** for Dependency injection
- **Jet pack compose** for UI
- **Retrofit** for fetching data
- **RoomDatabase** as the persistent library
- **Pagination** to efficiently load in chunks
- **WorkManager** for fetching news periodically
- **Coil** for image loading
- **Tinylog** for log rotation and debugging


# Naming some more concepts used in the application
- **Debounce operator** - For efficiently searching the news and not making repetitive unnecessary api calls
- **FlatMapLatest** - Making the api call for the latest entered result and ignoring the older ones
- **Filter** - Filtering out the required data from the raw data fetched from the server
- **FlatMapConcat** - Transforms each value of the flow sequentially
- **Map** - Transforms each value of the flow item into another type
- **Coroutine** - For performing asynchronous fetching of data from db or network
- **Flow** - Asynchronous data type used
- **StateFlow** - Obervable asynchronous data structure used


# Detailed summary of the screens implemented
# TopheadlinesScreen
- Shows a generic list of news from the database which is populated by news fetched from the newsApi.
- And on clicking on each of the news item, we navigate to a more detailed version of the news on browser.
# NewsSourceScreen
- Shows a list of news sources/news content providers.
- And on clicking on each of the source we navigate to their page.
# LanguagesScreen
- Shows a list of languages in which we have the news available.
- On clicking on each of the language we get to see the news in that particular language(**NewsByLanguageScreen**).
# NewsByLanguageScreen
- Shows a list of news in a particular language.
- And on clicking on each of the news item, we navigate to a more detailed version of the news on browser.
# CountriesScreen
- Shows a list of the countries whose news we have from the server.
- On clicking on each of the countries, we get to see the news from that country(**NewsByCountryScreen**).
# NewsByCountryScreen
- Shows a list of news of a particular country.
- And on clicking on each of the news item, we navigate to a more detailed version of the news on browser.
# InstantSearchScreen 
- It has a search bar to search for news related to a particular keyword.
- And when the user is done with typing the keyword, it shows the list of news related to that keyword.
- And on clicking on each of the news item, we navigate to a more detailed version of the news on browser.

  
# Attaching the screenshots of the pages for reference
![NewsApplicationScreens](https://github.com/siddhant123-geek/MyNewsApplicationProjectWithJetpackComposeAndDaggerHilt/assets/82453362/29b99236-44fb-46b7-9422-11c8dc9498d2)


# Dependencies used 
# UI(Jetpack Compose)
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation (platform("androidx.compose:compose-bom:2023.03.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

# Navigation
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("androidx.navigation:navigation-compose:2.6.0")

# Room Db
    implementation ("androidx.room:room-runtime:2.5.0")
    implementation ("androidx.room:room-ktx:2.5.0")
    kapt ("androidx.room:room-compiler:2.5.0")

# Pagination
    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation ("androidx.paging:paging-compose:3.2.1")

# Work Manager
    implementation ("androidx.work:work-runtime-ktx:2.7.0")

# TinyLog
    implementation("org.tinylog:tinylog-api:2.7.0")
    implementation("org.tinylog:tinylog-impl:2.7.0")

# Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

# Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")


# Coil 
    implementation ("io.coil-kt:coil-compose:2.4.0")


# The project structure
```bash
├───androidTest
│   └───java
│       └───com
│           └───example
│               └───newsapplicationprojectdaggerhiltjetpackcompose
│                       ExampleInstrumentedTest.kt
│
├───main
│   │   AndroidManifest.xml
│   │
│   ├───java
│   │   └───com
│   │       └───example
│   │           └───newsapplicationprojectdaggerhiltjetpackcompose
│   │               │   MainActivity.kt
│   │               │   MyNewsApplication.kt
│   │               │
│   │               ├───data
│   │               │   │   MyNewsFetchWork.kt
│   │               │   │
│   │               │   ├───api
│   │               │   │       NetworkService.kt
│   │               │   │
│   │               │   ├───local
│   │               │   │   │   DataBaseService.kt
│   │               │   │   │   MyAppDataBase.kt
│   │               │   │   │   MyAppDataBaseService.kt
│   │               │   │   │
│   │               │   │   ├───dao
│   │               │   │   │       ArticleDao.kt
│   │               │   │   │       SourceDao.kt
│   │               │   │   │
│   │               │   │   └───entity
│   │               │   │           Article.kt
│   │               │   │           Source.kt
│   │               │   │
│   │               │   └───model
│   │               │           ApiArticle.kt
│   │               │           ApiSource.kt
│   │               │           ContentToSee.kt
│   │               │           Country.kt
│   │               │           HomeItem.kt
│   │               │           Language.kt
│   │               │           NewsSourcesResponse.kt
│   │               │           TopHeadlineResponse.kt
│   │               │
│   │               ├───di
│   │               │   └───module
│   │               │           ApplicationModule.kt
│   │               │
│   │               ├───repository
│   │               │       NewsSourcesRepository.kt
│   │               │       TopHeadlineRepository.kt
│   │               │       TopHeadlingPagingSource.kt
│   │               │
│   │               ├───ui
│   │               │   │   HomeScreen.kt
│   │               │   │
│   │               │   ├───base
│   │               │   │       CommonUi.kt
│   │               │   │       NewsNavigation.kt
│   │               │   │       Qualifiers.kt
│   │               │   │       UiState.kt
│   │               │   │
│   │               │   ├───country
│   │               │   │       CountriesScreen.kt
│   │               │   │
│   │               │   ├───instantsearch
│   │               │   │       InstantSearchScreen.kt
│   │               │   │       InstantSearchViewModel.kt
│   │               │   │
│   │               │   ├───language
│   │               │   │       LanguagesScreen.kt
│   │               │   │
│   │               │   ├───newsbycountry
│   │               │   │       NewsByCountryScreen.kt
│   │               │   │       NewsByCountryViewModel.kt
│   │               │   │
│   │               │   ├───newsbylanguage
│   │               │   │       NewsByLanguageScreen.kt
│   │               │   │       NewsByLanguageViewModel.kt
│   │               │   │
│   │               │   ├───newssource
│   │               │   │       NewsSourceScreen.kt
│   │               │   │       NewsSourceViewModel.kt
│   │               │   │
│   │               │   ├───theme
│   │               │   │       Color.kt
│   │               │   │       Theme.kt
│   │               │   │       Type.kt
│   │               │   │
│   │               │   ├───topheadline
│   │               │   │       TopheadlineScreen.kt
│   │               │   │       TopHeadlineViewModel.kt
│   │               │   │
│   │               │   └───topheadlineswithpaging
│   │               │           TopheadlinesWithPagingScreen.kt
│   │               │           TopHeadlinesWithPagingViewModel.kt
│   │               │
│   │               └───utils
│   │                       AppConstants.kt
│   │                       IsoCodes.kt
│   │                       NetworkHelper.kt
│   │
│   ├───res
│   │   ├───dimens
│   │   ├───drawable
│   │   │       ic_launcher_background.xml
│   │   │       ic_launcher_foreground.xml
│   │   │
│   │   ├───mipmap-anydpi-v26
│   │   │       ic_launcher.xml
│   │   │       ic_launcher_round.xml
│   │   │
│   │   ├───mipmap-hdpi
│   │   │       ic_launcher.webp
│   │   │       ic_launcher_round.webp
│   │   │
│   │   ├───mipmap-mdpi
│   │   │       ic_launcher.webp
│   │   │       ic_launcher_round.webp
│   │   │
│   │   ├───mipmap-xhdpi
│   │   │       ic_launcher.webp
│   │   │       ic_launcher_round.webp
│   │   │
│   │   ├───mipmap-xxhdpi
│   │   │       ic_launcher.webp
│   │   │       ic_launcher_round.webp
│   │   │
│   │   ├───mipmap-xxxhdpi
│   │   │       ic_launcher.webp
│   │   │       ic_launcher_round.webp
│   │   │
│   │   ├───values
│   │   │       colors.xml
│   │   │       dimens.xml
│   │   │       strings.xml
│   │   │       themes.xml
│   │   │
│   │   ├───values-night
│   │   │       themes.xml
│   │   │
│   │   └───xml
│   │           backup_rules.xml
│   │           data_extraction_rules.xml
│   │
│   └───resources
│           tinylog.properties
│
└───test
    └───java
        └───com
            └───example
                └───newsapplicationprojectdaggerhiltjetpackcompose
                        ExampleUnitTest.kt
```
