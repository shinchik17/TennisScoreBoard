# Tennis Scoreboard Pet Project
[![Java CI/CD with Maven&Docker](https://github.com/shinchik17/tennis-scoreboard/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/shinchik17/tennis-scoreboard/actions/workflows/maven.yml)
[![Deploy container on a remote server](https://github.com/shinchik17/tennis-scoreboard/actions/workflows/ssh-deploy.yml/badge.svg?branch=main)](https://github.com/shinchik17/tennis-scoreboard/actions/workflows/ssh-deploy.yml)
---

## General info
This project provide simple simulation of tennis matches:
- creating new match;
- score calculating;
- view finished matches.
 
Tennis rules may vary a lot at different tournaments. 
So for this project were established following rules.
- Match ends after one player won 2 sets (best of 3).
- At score 6:6 tie-break (till 7 points) is starting.

## **Technologies**
- **Jakarta EE (Servlet API)**: Provides a standard way to handle HTTP requests and responses.
- **Java Server Pages**: A collection of technologies that provides creating dynamically generated web pages based on HTML, XML etc.
- **Tomcat**: A robust servlet container that implements the Servlet API. Tomcat serves as a web server and provides an
  environment where Java code can run.
- **H2 Database**: In this project, SQLite serves as an in-memory database to store data persistently during runtime.
- **Hibernate**: A powerful framework for mapping an object-oriented domain model to a relational database.
- **HikariCP**: A connection pool that manages a pool of database connections, improving the performance of
  database operations by reusing connections rather than creating new ones for every request.
- **ModelMapper**: Simplifies the task of mapping objects to each other.
- **Maven**: A build automation tool used primarily for Java projects.
- **Docker**: A tool for delivering software in packages called containers that. Uses OS-level virtualization.
- **GitHub Actions**: A continuous integration and continuous delivery (CI/CD) platform that allows you to automate your build, test, and deployment pipeline.


## **Getting started**

  Use Docker to pull project image and run the project in detached mode 
  (ensure that port 80 is free on your local computer):

  ```sh
  docker run -d -p 80:8080 shinchik17/tennis-scoreboard
  ```

  Once the project is running, you can access the project at:

  ```sh
  http://localhost/tennis-scoreboard/
  ```



