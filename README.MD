# Payment Orchestration Layer

## Overview
Payment Orchestration Layer is a backend system built using Spring Boot that manages payment processing, authentication, and event-driven communication.

The system provides secure APIs for creating and managing payments with JWT authentication and role-based authorization.

---

## Features

- JWT Authentication
- Role-Based Access Control (ADMIN / USER)
- Payment APIs
- Kafka Event Messaging
- PostgreSQL Database
- Redis Integration
- Docker & Docker Compose
- Swagger API Documentation
- Exception Handling

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- PostgreSQL
- Redis
- Apache Kafka
- Docker
- Swagger/OpenAPI

---

## API Endpoints

### Authentication

POST /auth/login

### Payments

GET /api/payments

POST /api/payments

---

## Running Project

Clone repository:

```bash
git clone https://github.com/Mannat30/PAYMENT-ORCHESTRATION-LAYER.git
