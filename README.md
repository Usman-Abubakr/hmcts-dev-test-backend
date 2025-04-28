# HMCTS Dev Test Backend

This is a simple Spring Boot application for task management, connected to a PostgreSQL database and fully containerized with Docker.

Live deployment using the Docker instructions: https://hmcts-dev-test.usman-a.dev

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

#### Running with Docker Compose (Backend only)

- `docker-compose up --build`
- Accessible by default on `http://localhost:4000`
- Ports and database details can be updated by editing `Dockerfile` and `docker-comepose.yml`

#### Running with Docker Compose (Backend + Frontend)

1. Create a folder called `hmcts-dev-test` and clone this repository and the [backend](https://github.com/Usman-Abubakr/hmcts-dev-test-backend)
2. In the new folder, create a new docker network with `docker network create hmcts-network`
3. Copy `docker-compose.yml.example` from this project into the new folder and rename to `docker-compose.yml`
4. Run `docker-compose up --build`, the project should be accessible on https://localhost:3100/

- The Project structure should look similar like this:
```
hmcts-dev-test/
│
├── hmcts-dev-test-backend/
│   ├── docker-compose.yml.example  ← (copy and rename this to `docker-compose.yml` into the root)
│   ├── src/
│   └── Dockerfile
│
├── hmcts-dev-test-frontend/
│   ├── docker-compose.yml.example  ← (ignore if copied from backend project)
│   ├── src/
│   └── Dockerfile
│
└── docker-compose.yml  ← (new file here, after copying example)
```

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
