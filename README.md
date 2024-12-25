# Overview
This app leverages TheMovieDB API to display a list of popular people, allows users to search for them, and view their details with associated images. It focuses on simplicity, modularity, and a user-friendly interface, while adhering to clean coding principles.

## Features
* Popular People List\
Display a list of popular people using /person/popular.
Infinite scrolling implemented using Paging 3.

* Search People\
Search functionality using /search/person.
Real-time query updates with debounce logic.
Infinite scrolling for search results.

* Person Details View\
Basic details fetched from /person/{person_id}.
Grid view of images fetched from /person/{person_id}/images.

## Technology Stack
Language: Kotlin\
Architecture: MVVM\
Dependency Injection: Hilt\
Networking: Retrofit + OkHttp\
Image Loading: Coil\
Pagination: Paging 3\
UI Framework: XML Layouts\
Min SDK: API 21 (Lollipop)

## Development Plan
### Setup
Configure project dependencies and API key.
Test APIs using the "Try-it-out" feature.

* Popular People Screen\
Build RecyclerView with GridLayoutManager.
Integrate Paging 3 for infinite scrolling.

* Search Screen\
Add SearchView in the toolbar.
Debounce user input to reduce API calls.

* Details Screen\
Display details (name, biography, etc.) using /person/{person_id}.
Display images in a grid view using /person/{person_id}/images.
