# ServiceNow Clone - Backend

A scalable and secure RESTful backend application for a ServiceNow-inspired Ticket Management System. Built using Spring Boot, this project provides APIs for authentication, user management, ticket lifecycle management, notifications, dashboard analytics, email services, and file attachments.

---

## Overview

The backend is responsible for handling all business logic and data management of the ServiceNow Clone application. It exposes REST APIs consumed by the React frontend and implements role-based access control using JWT authentication.

The system allows administrators to manage users and tickets while enabling users to create, update, and monitor their assigned tickets.

---

## Features

### Authentication & Authorization

- JWT Authentication
- Secure Login API
- User Registration
- Role-Based Access Control
- Admin and User Roles

### User Management

- Create Users
- View Users
- Update User Details
- Delete Users
- Role Assignment

### Ticket Management

- Create Tickets
- Update Tickets
- Delete Tickets
- Assign Tickets
- Ticket History
- Ticket Status Management
- Priority Management

### Dashboard

- Total Users
- Total Tickets
- Open Tickets
- Closed Tickets
- High Priority Tickets
- Assigned Tickets

### Notification System

- Ticket Update Notifications
- Ticket Status Notifications
- Ticket Assignment Notifications

### Email Service

- Email Notifications
- Ticket Assignment Emails
- Ticket Status Update Emails

### Attachment Module

- Upload Attachments
- Download Attachments
- Delete Attachments
- View Ticket Attachments

---

## Tech Stack

| Technology | Purpose |
|------------|----------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication |
| JWT | Token Authentication |
| Spring Data JPA | ORM |
| Hibernate | Database Mapping |
| MySQL | Database |
| Maven | Dependency Management |
| Railway | Deployment |
| Java Mail Sender | Email Service |

---

## Project Structure

```
src
 ├── Auth
 ├── config
 ├── controller
 ├── dto
 ├── entity
 ├── exception
 ├── repository
 ├── security
 ├── service
 └── Main.java
```

---

## REST APIs

### Authentication

POST /auth/login

POST /auth/register

---

### Users

GET /users

GET /users/{id}

POST /users

PUT /users/{id}

DELETE /users/{id}

---

### Tickets

GET /tickets

GET /tickets/{id}

POST /tickets

PUT /tickets/{id}

DELETE /tickets/{id}

---

### Attachments

POST /attachments/{ticketId}

GET /attachments/ticket/{ticketId}

GET /attachments/download/{id}

DELETE /attachments/{id}

---

## Database

### Tables

- users
- tickets
- notifications
- attachments

---

## Security

- JWT Authentication
- Protected REST APIs
- Role-Based Authorization
- CORS Configuration

---

## Deployment

Backend is deployed using Railway.

Environment Variables

```
DB_URL
DB_USERNAME
DB_PASSWORD

MAIL_USERNAME
MAIL_PASSWORD

PORT
```

---

## Future Enhancements

- BCrypt Password Encryption
- Refresh Token Authentication
- Cloud File Storage (AWS S3 / Cloudinary)
- Forgot Password
- Email Verification
- Swagger Documentation
- Docker Containerization
- Unit Testing
- Integration Testing

---
**SAI CHANDU JALLI**
<img width="1310" height="727" alt="image" src="https://github.com/user-attachments/assets/f333a9ef-220e-431a-9b71-be883b1ed9fc" />
<img width="1359" height="767" alt="image" src="https://github.com/user-attachments/assets/80713762-1453-4064-a585-fcd9280b40fe" />
<img width="1365" height="767" alt="image" src="https://github.com/user-attachments/assets/045ccf35-c799-46f7-b8af-7a04f89fd11e" />
<img width="1314" height="725" alt="image" src="https://github.com/user-attachments/assets/65478ce2-2dec-48a2-b635-6a885d7cbfe4" />
![Uploading image.png…]()





