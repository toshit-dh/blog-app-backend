### Blog App Backend - Spring Boot

Welcome to the backend repository of our Blog App, powered by Spring Boot! 🚀

#### Features:
- **Spring Security**: Secure your APIs with authentication and authorization.
- **JWT Authentication**: Implement token-based authentication for secure user sessions.
- **SQL Database Driver**: Use SQL database driver for efficient data storage and retrieval.
- **JPA (Java Persistence API)**: Simplify database operations with JPA for object-relational mapping.
- **Hibernate**: ORM framework to manage database interactions in a Java environment.

#### Getting Started:
Follow these steps to get the backend up and running:

1. **Clone the repository**:
   ```
   git clone https://github.com/toshit-dh/blog-app-backend.git
   ```
   
2. **Setup Database**:
   - Configure your SQL database settings in `application.properties`.
   - Ensure the database schema is created and configured correctly.

3. **Build and Run**:
   - Build the project using Maven:
     ```
     mvn clean install
     ```
   - Run the application:
     ```
     mvn spring-boot:run
     ```

4. **Explore APIs**:
   - Once running, explore the RESTful APIs provided by the backend.
   - Use tools like Postman or curl to interact with the endpoints.

5. **Authentication**:
   - Utilize JWT tokens for authentication. Generate tokens for authorized access to APIs.
