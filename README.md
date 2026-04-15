# TaskManagementAssignment

## Overview

This is the backend system for the fintech application. It handles user onboarding and transactions. It also includes OTP verification and JWT authentication for security.

## Tech Stack

* i use Java and Spring Boot for this project

* i use Spring Security, with JWT for authentication

* i use JPA and Hibernate for database operations

* database is MySQL

## Features

* Users can register with OTP verification

* Users can login with JWT-based authentication

* When a user registers an account is automatically created with a balance of 1000

* Users can transfer money to users

* Users can view their transaction history

## APIs

### Auth

* We have an API to register users at `POST /auth/register`

* We have an API to verify OTP at `POST /auth/verify-otp`

* We have an API to login at `POST /auth/login`

### Transactions

* We have an API to transfer money at `POST /transaction/transfer`

* We have an API to get transaction history at `GET /transaction/transactions/{userId}`

## Security

* i use JWT authentication to secure our APIs

* The following APIs are public: register, login and verify-otp

## Setup

To set up this project you need to run the following commands:

```bash

clean install

mvn spring-boot:run

```

You also need to configure the database in the `application.properties` file.

## Testing

* i use JUnit and Mockito for testing

* i wrote tests that cover the transfer logic and authentication flow

## Structure

The code structure is as follows:

```text

Controller → Service → Repository → DB

```

## Key Concepts

* i use REST APIs to interact with the backend

* i use JWT security to authenticate users

* i implement transaction management to handle money transfers

* i implement exception handling to handle any errors that may occur in the fintech backend system.
