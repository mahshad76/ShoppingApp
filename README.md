# ShoppingApp
The ShoppingApp is a modern mobile application designed to provide users with a clean, intuitive interface for browsing a comprehensive list of products, complete with ratings. Users can select any item to view rich details, and utilize persistent "Like" functionality to curate their personal favorites list, which remains saved across sessions. The products are retrieved from the [FakeStoreAPI](https://fakestoreapi.com/).
<video src="https://github.com/user-attachments/assets/0aeadd29-bd53-408c-835b-f9f481050cb4" controls></video>

# ✨ Key Features

The application focuses on an efficient and engaging product exploration experience:
## 🔎 Browsing & Discovery
* Comprehensive Product List (Home Screen): Displays a full, scrollable list of available products.
## 📖 Detailed View
* Product Details: Selecting a product navigates the user to a dedicated screen to view detailed information about the item.
## ❤️ Personalization & Persistence
* Product Liking (Favorites): Users can easily "like" any product from the detail screen.
* Favorites Persistence: All liked products are saved locally, ensuring the user's curated favorites list is retained and available even after closing and restarting the application.

# 🏗️ Architecture
This application is built using the Model-View-Presenter (MVP) architectural pattern to ensure a clear separation of concerns, which improves testability and maintainability across the codebase. The MVP structure divides the application into three key components:
## 1. View (Passive Layer)
The View layer is handled by Activities and Fragments.
- Responsibility: Displays data to the user and captures user interactions.
- Key Principle: The View is "dumb" and contains minimal logic; it simply forwards user actions to the Presenter and implements methods the Presenter calls to update the UI.
## 2. Presenter (Logic Layer)
The Presenter acts as the middle-man between the View and the Model. It is the heart of the business logic.
- Responsibility: Processes user input from the View, retrieves data from the Model, applies business logic, and tells the View exactly what to display.
- Key Principle: The Presenter is framework-independent, meaning it contains no references to Android classes, making it highly unit-testable.
## 3. Model (Data Layer)
The Model is responsible for managing the application's data sources.
- Responsibility: Handles all data operations, including remote API calls and local database transactions.
- Key Principle: The Model is completely unaware of the View and the Presenter, ensuring the data logic can be easily swapped or updated without affecting the UI.

# 🛠️ Technologies & Libraries
1.  **Core Development Stack & UI**
* Kotlin: The primary, modern programming language, chosen for its safety features, conciseness, and seamless integration with the Android framework.
* XML Layouts & ConstraintLayout: Utilized for declaring flexible and responsive user interface views. ConstraintLayout is specifically used to create flat and efficient view hierarchies.

2. **Networking & API Communication**

* Retrofit, OkHttp, & Gson: This combination forms the application's robust network layer. Retrofit provides a type-safe HTTP client for API interaction; OkHttp efficiently handles the underlying connection protocols; and Gson automatically serializes/deserializes product data. The Logging Interceptor is included for network debugging.

3. **Reactive Programming**

* RxJava 2 & RxAndroid: A comprehensive library suite for managing complex asynchronous operations and data streams. Used extensively for handling background tasks like network calls and database access, integrating seamlessly via the RxJava Adapter and utilizing RxJava Extensions for advanced stream manipulation.

3. **Dependency Injection**

* Dagger 2: A powerful, compile-time dependency injection framework used to manage object dependencies (such as Presenters, Repositories, and Services), which is critical for supporting the MVP architecture and facilitating unit testing.

4. **Data Persistence**

* Room: The official Android Persistence Library. It provides an abstraction layer over SQLite, used in the Model layer to persistently save the user's "liked" products. It fully supports RxJava 2 to enable reactive database monitoring.

5. **Image Handling**

* Glide: A dedicated, high-performance image loading library used for fetching, caching, and efficiently displaying product images without causing memory leaks or UI slowdowns.
