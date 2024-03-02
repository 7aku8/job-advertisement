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

- `POST /api/v1/auth/login`: Authenticate a user.
- `POST /api/v1/auth/register`: Register a new user.
- `POST /api/v1/jobs`: Create a new job advertisement (requires admin privileges).
- `GET /api/v1/jobs`: Get a list of all verified job advertisements.

Please refer to the API documentation for more details on the request and response formats.

## Contributing

Please read `CONTRIBUTING.md` for details on our code of conduct, and the process for submitting pull requests to us.

## License

This project is licensed under the MIT License - see the `LICENSE.md` file for details.