# TC3004B

## Task Management System for Oracle

### How to run the project

Use:

```bash
cd MtdrSpring/backend && mvn clean install && mvn spring-boot:run
```

The project will be available in `http://localhost:8080/` after around 30-40 seconds.
You can stop the the project using `CTRL + C` on Windows / Linux or `CMD + C` in MacOS.
After that you can rerun the project as many times as you want using the following command:

```bash
mvn clean install && mvn spring-boot:run
```

Be sure to be in the `/backend` repository when running these commands.

### Frontend

The frontend is simply a wrapper to do requests to the backend, it makes requests to all the endpoints listed in `./BACKEND.md`. Ever since our first iteration it has grown into its own ecosystem and using its own IDE (e.g. Intellij) has become very useful. 

It is actively documented in `./FRONTEND.md`.

### Backend 

As stated before, it used to be a single entity in the route `/todolist` but ever since then it has grown into its own environment with different entities interacting with one another. I would advice reading `./BACKEND.md` for more information about it. 