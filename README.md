# StarWarsSearch
This is an android app that consumes the [Star wars API](https://swapi.dev/) to search characters and get to display their details such as name, specie, vehicles e.t.c.
The app is built using clean architecture and MVVM pattern.

The app is divided into the following modules:
- app
- data
- domain

### app
This contains all things related to the UI layer along with any other classes that are required for the UI.

### data
This module contains a networking layer and a database layer. The networking layer handles data interaction with the network and provides data to the presentation layer.
The database is responsible for persisting data locally and serve the data to the presentation layer(This is still under development :hammer_and_pick:)

### domain
The domain module defines the models core to the app, interactors(usecases) and repositories contract.

## Libraries
- ViewModel
- LiveData
- Room
- Data binding
- Navigation component
- Kotlin Flows
- Kotlin Coroutines
- Retrofit
- okhhtp
- Gson
- Truth
- MockWebServer

Some features are still under development :hammer_and_pick: