# HMCTS Dev Test Backend

This is a simple Spring Boot application for task management, connected to a PostgreSQL database and fully containerized with Docker.

---

## Tech Stack

- Java 21
- Spring Boot 3
- PostgreSQL 17
- Gradle
- Docker & Docker Compose
- JUnit 5 & Mockito (for testing)

---

## How to Run

### Docker:

#### Prerequisites

- Docker & Docker Compose

#### Running with Docker Compose

- `docker-compose up --build`
- Accessible by default on `http://localhost:8080`
- Ports and database details can be updated by editing `Dockerfile` and `docker-comepose.yml`


### Local:

#### Prerequisites

- Java & Gradle for local development

#### Build & Run

- Run `./gradlew build` to build
- Run `./gradlew bootRun` to run in terminal
- Accessible by default on `http://localhost:4000`
- Ports and database details can be updated by editing `.env`

---

## Endpoints

- GET `/` : Welcome
- GET `/tasks` : Read all tasks
- GET `/tasks/:id` : Read Specific task
- POST `/tasks` : Create task, `Task` body required without id (auto generated)
- PUT `/tasks` : Update task, `Task` body required with id of desired task
- DELETE `/:id` : Delete task

### Schema

```json
{
  "id": 1,
  "caseNumber": "ABC123",
  "title": "Title of task",
  "description": "A description of the task",
  "status": "to do",
  "dueDate": "2025-04-27"
}
````

---

## Improvements

Here are some features and improvements that could be made, which did make it due to time constrains and scope of project:

- Authorisation
- Complete testing
- Data sanitisation


