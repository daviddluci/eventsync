# EventSync

EventSync is a Spring Boot REST API application that allows users to create and view events, submit feedback, and analyze the sentiment of submitted feedback using an external AI sentiment analysis API.

# Live EventSync Demo

You can inspect and try out the frontend version of EventSync, which communicates with this API, here:

https://eventsync-50r.pages.dev/


Alternatively, you can call API service through Postman, curl, or any other tool here:  

https://eventsync-final.onrender.com/  

Endpoints for this service are described below in the All set section

**Warning:** This service is free and will go offline after 15 minutes of inactivity. It may take up to a minute for the service to reboot when accessed again.

## Features

- Create new event
- List all events
- Submit feedback for an event
- Return sentiment breakdown for the event (positive, etc..)

## Technologies

- Java 17+
- Spring Boot
- Spring Web
- Spring JPA
- H2 (in-memory database)
- HuggingFaceAPI Sentiment Analysis Model (cardiffnlp/twitter-roberta-base-sentiment)

## Getting Started

### Must-haves

- Java 17+
- Maven
- Docker
- API key for sentiment analysis provider (One is provided for demo purpose, feel free to use it)

### Installation

1. Clone the repository:

```powershell
git init
git clone https://github.com/daviddluci/eventsync.git
cd eventsync/eventsync-backend-api
```

2. (Optional) Set the environment variable for the sentiment analysis API key, or leave the one provided:

src\main\resources\application.properties <-- huggingface.api.key=   <--Add you environment variable here.

3. Build the project using Maven:

```powershell
mvn clean package
```

4. Run the application:

```powershell
java -jar target/eventsync-1.0.0.jar
```

Alternatively, run it directly through Maven:

```powershell
mvn spring-boot:run
```

## Build Docker Image

### If you want to run the application using Docker, build and run it with:

### Docker file is already provided in repository, feel free to modify it to your needs.

1. Build Docker Image

```powershell
docker build -t eventsync-backend .
```

2. Run Docker Image

```powershell
docker run -p 8080:8080 eventsync-backend
```

## All set!

### API should now be accessible on your local machine at::

### http://localhost:8080

### Available Endpoints:

- http://localhost:8080/events  <-- POST – create new event

- http://localhost:8080/events  <-- GET – list all events

- http://localhost:8080/events/{eventId}/feedback <-- POST – submit feedback for an event

- http://localhost:8080/events/{eventId}/summary <-- GET – return sentiment breakdown for the event

Now you can call this API service on your local machine using Postman, curl, or any tool you prefer!