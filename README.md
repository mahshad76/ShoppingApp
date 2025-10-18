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

