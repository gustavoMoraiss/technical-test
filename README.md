# Technical Test - App

The project is a challenge to develop an app using the free API from github.com.

I utilized Jetpack Compose in this project and followed the best practices to developer the app's behaviors.

The requirements:

### RepositoriesScreen

- List all Java repositories and apply pagination on the list screen, with infinite scroll and increasing the
  page parameter.

- If the API don't send a answer, i will apply retry function.

- When you touch on RepositoryItem, i will navigate you to PullRequestsScreen

### PullRequestsScreen

- List all Pull Requests from the repository touched, with scroll and back button for navigate back to RepositoriesScreen.

- Here, i will show you some attributes from API response.

## Core Libraries

#### Retrofit: For RESTful API communication.
#### Hilt: For dependency injection.
#### Coil: For image loading.
#### Kotlin Flow: For reactive streams.
#### Paging 3: For pagination of data.
#### Jetpack Compose: For building UI screens declaratively.
#### JUnit, Mockito: For unit testing.
#### All Screens contains accessibility.


