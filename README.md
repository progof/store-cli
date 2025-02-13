# Store CLI Application

This is a console application for a store, implemented in Java using the Spring Framework. The application offers the following features:

- **User Registration and Authentication**  
  Users can create accounts and log in. During registration, the uniqueness of the username is verified to avoid duplication.

- **Cart Functionality**  
  Users can add products to the cart, view the cart contents, remove products, and place orders. When placing an order, the availability of the product in stock is verified.

- **Product Catalog Viewing**  
  A list of available products is displayed along with information on price and availability.

- **Debug Mode**  
  A special menu is provided for viewing data from the database, such as lists of users and products.

- **Data Initialization**  
  When the application starts, the database is initialized and sample products (for example, various types of tea) are added using the `InitData.addSampleTeas()` method. Checks are implemented to avoid data duplication.

## Technologies

- **Java 20** (or newer)
- **Spring Framework** — for managing dependencies and application components
- **SQLite** — database
- **JDBC** — for database operations
- **Lombok** — to reduce boilerplate code (using annotations like `@RequiredArgsConstructor`, etc.)
- **Maven/Gradle** — for building the project and managing dependencies

## Project Structure

- **Core**  
  The main class of the application that handles launching, authentication, and switching between modes (store and debug).

- **GUI**  
  Components for interacting with the user via the console:
  - `AuthGUI` — for registration and login.
  - `StoreGUI` — for the store menu and displaying the product list.
  - `DebugGUI` — for debugging, to view data from the database.

- **Controller and Service**  
  The logic for handling the cart, products, and users, implemented in the respective classes (e.g., `CartController`, `ProductService`, `UserService`).

- **DB**  
  Components for interacting with the database:
  - `InitData` — for initializing the database and adding sample products.
  - `DebugDB` — for executing queries to debug and view data.

## How to Run the Application

### Prerequisites

- **JDK 20** (or newer) installed
- **Maven** or **Gradle** for building the project
- **SQLite** (the JDBC driver for SQLite is included as a dependency, so no additional installation is required)

### Steps to Run

1. **Clone the Repository**

   ```bash
   git clone https://github.com/progof/store-cli.git
   cd store-cli 
