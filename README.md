# Globetrotter Backend
Globetrotter, a full-stack web app where users get cryptic clues about a famous place and must guess which destination it refers to. Once they guess, they’ll unlock fun facts, trivia, and surprises about the destination!

## Table of Contents
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

## Features
- User registration with a unique username.
- Game session creation and persistence in a PostgreSQL database.
- Clue generation and guess verification with feedback.
- RESTful API design for frontend integration.
- Planned Redis integration for session management (future enhancement).

## Technology Stack
- **Language**: Java 23
- **Framework**: Spring Boot 3.x
- **Database**: PostgreSQL 15
- **Build Tool**: Maven

## Prerequisites
- Java 23 JDK installed
- Maven 3.8.x or higher
- PostgreSQL 15 installed and running
- Git for cloning the repository
- (Optional) A REST client (e.g., Postman) for testing APIs

## Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/globetrotter-backend.git
   cd globetrotter
