# Job Advertisement API

This is a RESTful API for job advertisements. It allows the creation of tasks which become visible to visitors once
verified by an admin. The project is built using Java, Spring Boot, and PostgreSQL, and it runs inside a Docker
container.

## Prerequisites

- Java 8 or higher
- Docker
- Docker Compose

## Getting Started

1. Clone this repository to your local machine.
    ```
    git clone https://github.com/your-repository-url.git
    ```
2. Navigate to the project directory.
    ```
    cd your-project-directory
    ```
3. Run the Docker Compose command to start the PostgreSQL database.
    ```
    docker-compose up
    ```
4. Once the database is up and running, you can start the application. Open a new terminal window and navigate to the
   project directory, then run the following command:
    ```
    ./mvnw spring-boot:run
    ```
   If you're using Windows, use `mvnw` instead of `./mvnw`.

The application will be accessible at `http://localhost:8080`.

## API Endpoints

All available endpoints are available in exported Postman collection in the `postman` directory.