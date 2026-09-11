
# VisionERP

### AI-Enabled Enterprise Resource Planning Platform

VisionERP is a full-stack enterprise management platform designed to centralize **employees, departments, attendance, visitors, analytics, notifications, auditing, and AI-assisted workflows** in a secure and scalable architecture.

Built with **React + TypeScript, Spring Boot, PostgreSQL, FastAPI, OpenCV, JWT, WebSockets, and Flyway**, the project demonstrates production-oriented software engineering across frontend, backend, database, security, APIs, and AI services.

---

## 🚀 Highlights

- 🔐 JWT-based authentication and role-based access control
- 👥 Employee and department management
- 🕒 Attendance tracking with check-in workflows
- 🧑‍💼 Visitor registration and management
- 📊 Dashboard and analytics
- 🤖 AI-assisted enterprise workflows
- 👁️ Computer-vision service using OpenCV
- 🔔 Real-time notifications using WebSockets
- 📝 Audit logging for important operations
- 🗄️ PostgreSQL persistence with Flyway migrations
- 📖 REST APIs with OpenAPI / Swagger documentation
- 🧪 Automated CI checks with GitHub Actions
- 🐳 Docker Compose support
- 🏗️ Modular frontend, backend, and AI-service architecture

---

## 🏛️ Architecture

VisionERP follows a modular multi-service architecture:

```text
                         ┌──────────────────────┐
                         │      React UI        │
                         │  TypeScript + Vite   │
                         └──────────┬───────────┘
                                    │
                              REST / WebSocket
                                    │
                                    ▼
                    ┌─────────────────────────────┐
                    │       Spring Boot API       │
                    │                             │
                    │ • Authentication / JWT      │
                    │ • RBAC                      │
                    │ • Employees                 │
                    │ • Departments               │
                    │ • Attendance                │
                    │ • Visitors                  │
                    │ • Analytics                 │
                    │ • Audit Logs                │
                    │ • Notifications             │
                    └──────────────┬──────────────┘
                                   │
                       ┌───────────┴───────────┐
                       │                       │
                       ▼                       ▼
              ┌─────────────────┐     ┌─────────────────┐
              │   PostgreSQL    │     │   AI Service    │
              │                 │     │                 │
              │ • Users         │     │ FastAPI         │
              │ • Employees     │     │ OpenCV          │
              │ • Attendance    │     │ Vision Analysis │
              │ • Visitors      │     │ AI Tools        │
              │ • Audit Logs    │     │                 │
              └─────────────────┘     └─────────────────┘

The repository also contains the architecture diagram:

✨ Features
🔐 Authentication & Security
JWT-based authentication
Stateless authentication
Role-based authorization
Protected REST endpoints
Password hashing
Environment-based configuration for secrets
Audit logging for important business operations

Supported roles include:

ADMIN
EMPLOYEE
👥 Employee Management

Administrators can manage employee records including:

Employee name
Email
Employee code
Department
Role
Employment status

Employee data is persisted through Spring Data JPA and PostgreSQL.

🏢 Department Management

VisionERP provides centralized department management for organizing employees across the organization.

Departments can be associated with employee records and used throughout the dashboard and analytics workflows.

🕒 Attendance Management

The attendance module supports:

Employee check-in
Attendance status
Check-in timestamp
Attendance source
Employee-based attendance records
Attendance audit logging

The frontend provides an employee-selection workflow for performing manual check-ins.

🧑‍💼 Visitor Management

The visitor module allows authorized users to register and manage visitors.

Visitor workflows include:

Visitor registration
Visitor information
Host employee association
Visit tracking
📊 Dashboard & Analytics

The dashboard provides a centralized view of enterprise activity.

Current dashboard capabilities include:

Employee statistics
Department statistics
Attendance overview
Visitor information
Activity information

The analytics layer is structured to support historical enterprise metrics as more production data becomes available.

🤖 AI Assistant

VisionERP includes an AI-assistant layer for enterprise queries and workflows.

The current implementation uses deterministic intent routing and backend tools rather than a live external LLM.

Example workflow:

User Request
     │
     ▼
AI Assistant
     │
     ▼
Intent Detection
     │
     ▼
Backend Tool
     │
     ▼
Enterprise Data
     │
     ▼
Response

This architecture keeps the assistant layer extensible so that an LLM can be integrated later without redesigning the underlying business APIs.

👁️ Computer Vision Service

The separate AI service is built with:

Python
FastAPI
OpenCV
NumPy

Current vision capabilities include:

Face detection
Image-quality analysis
Image hashing using SHA-256
Vision-analysis API endpoints

Important: The current implementation does not perform biometric identity recognition. It provides computer-vision analysis and is designed as an extensible foundation for future vision workflows.

🔔 Real-Time Notifications

The backend supports WebSocket communication using:

STOMP
Spring WebSocket

This provides the foundation for real-time notification and event-driven UI updates.

📝 Audit Logging

Important enterprise actions are recorded through an audit-log system.

Audit records can capture information such as:

User
Action
Entity
Entity ID
Timestamp
Relevant operation details

Example:

ADMIN
  │
  └── CHECK_IN
          │
          └── ATTENDANCE #2
                  │
                  └── Employee: EMP-001

This improves traceability and provides a foundation for enterprise compliance requirements.

🛠️ Tech Stack
Frontend
Technology	Purpose
React	UI framework
TypeScript	Type safety
Vite	Frontend tooling
Axios	HTTP communication
Recharts	Data visualization
Lucide	UI icons
Backend
Technology	Purpose
Java 21	Backend language
Spring Boot	Application framework
Spring Security	Authentication & authorization
JWT	Stateless authentication
Spring Data JPA	Persistence layer
Hibernate	ORM
PostgreSQL	Relational database
Flyway	Database migrations
WebSocket / STOMP	Real-time communication
Springdoc OpenAPI	API documentation
AI Service
Technology	Purpose
Python	AI service language
FastAPI	REST API framework
OpenCV	Computer vision
NumPy	Numerical processing
Pydantic	Data validation
DevOps & Tooling
Technology	Purpose
Docker	Containerization
Docker Compose	Multi-service orchestration
Git	Version control
GitHub Actions	Continuous integration
Maven	Java build system
npm	Frontend package management
📁 Project Structure
VisionERP/
│
├── frontend/
│   ├── src/
│   ├── package.json
│   └── vite.config.*
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   └── test/
│   ├── pom.xml
│   └── Dockerfile
│
├── ai-service/
│   ├── app/
│   ├── requirements.txt
│   └── Dockerfile
│
├── docs/
│   └── diagrams/
│
├── scripts/
│
├── .github/
│   └── workflows/
│
├── docker-compose.yml
├── .env.example
├── CONTRIBUTING.md
├── LICENSE
└── README.md
🔌 API Overview

The Spring Boot backend exposes REST APIs for the major enterprise modules.

Module	Purpose
Authentication	Login and authentication
Employees	Employee CRUD and management
Departments	Department management
Attendance	Attendance records and check-in
Visitors	Visitor registration and tracking
Dashboard	Enterprise overview
Analytics	Business metrics
Notifications	Notification workflows
Audit	System activity and audit records
API Documentation

When the backend is running, OpenAPI / Swagger documentation is available through Springdoc.

http://localhost:8080/swagger-ui/index.html

OpenAPI specification:

http://localhost:8080/v3/api-docs
🗄️ Database

VisionERP uses PostgreSQL as its primary relational database.

The backend uses:

Spring Data JPA
Hibernate
Flyway migrations

Core domain entities include:

User
Department
Employee
Attendance
Visitor
Notification
AuditLog

Database schema changes are version-controlled through Flyway migrations.

⚙️ Local Development
Prerequisites

Install:

Java 21+
Maven 3.9+
Node.js
npm
Python 3.12+
PostgreSQL
Git

Docker is optional for local development.

1. Clone the Repository
git clone https://github.com/AnushkaBharti2/VisionERP.git
cd VisionERP
2. Configure PostgreSQL

Create a PostgreSQL database:

CREATE DATABASE visionerp;

Then configure the backend environment variables.

Windows
set SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/visionerp
set SPRING_DATASOURCE_USERNAME=postgres
set SPRING_DATASOURCE_PASSWORD=<your-postgres-password>

set JWT_SECRET=<your-32-character-or-longer-secret>

set SEED_ADMIN_EMAIL=admin@visionerp.local
set SEED_ADMIN_PASSWORD=<your-admin-password>

set VISIONERP_ALLOWED_ORIGINS=http://localhost:5173
Linux / macOS
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/visionerp
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=<your-postgres-password>

export JWT_SECRET=<your-32-character-or-longer-secret>

export SEED_ADMIN_EMAIL=admin@visionerp.local
export SEED_ADMIN_PASSWORD=<your-admin-password>

export VISIONERP_ALLOWED_ORIGINS=http://localhost:5173

Never commit real passwords, JWT secrets, API keys, or other credentials to Git.

3. Start the Backend
cd backend
mvn spring-boot:run

The backend runs on:

http://localhost:8080
4. Start the AI Service

Open another terminal:

cd ai-service

Create a virtual environment:

Windows
python -m venv .venv
.venv\Scripts\activate
Linux / macOS
python3 -m venv .venv
source .venv/bin/activate

Install dependencies:

pip install -r requirements.txt

Start FastAPI:

uvicorn app.main:app --reload --port 8000

AI service:

http://localhost:8000

Health endpoint:

http://localhost:8000/health
5. Start the Frontend

Open another terminal:

cd frontend
npm install
npm run dev

Vite will provide the local development URL, typically:

http://localhost:5173
🐳 Docker

VisionERP also includes Docker Compose configuration for running the services together.

docker compose up --build

To stop the services:

docker compose down

Docker is recommended when the local environment supports Docker Desktop and the required virtualization/WSL configuration.

🧪 Testing & Continuous Integration

The repository uses GitHub Actions to validate the main application components.

The CI pipeline covers:

Frontend build
Backend build
AI-service dependency/security checks

This helps catch build and dependency issues before changes are merged.

🔒 Security Considerations

VisionERP was designed with several security principles in mind:

JWT-based authentication
Role-based access control
Password hashing
Stateless backend authentication
Environment-based secrets
Database migrations
Audit logging
Protected API endpoints
Dependency security checks in CI

For production deployment, additional hardening should include:

HTTPS/TLS
Secure secret management
Restricted CORS origins
Restricted actuator endpoints
Production-grade database credentials
Rate limiting
Centralized logging and monitoring
Secure infrastructure configuration
🧠 Engineering Decisions
Why Spring Boot?

Spring Boot provides a mature ecosystem for building secure, maintainable enterprise APIs with:

Spring Security
JPA/Hibernate
Validation
WebSockets
OpenAPI integration
Production-oriented configuration
Why PostgreSQL?

PostgreSQL provides reliable relational persistence and is well suited for structured enterprise data and transactional workflows.

Why FastAPI?

The AI service is isolated from the Java backend so computer-vision workloads can evolve independently from the core ERP application.

Why separate the AI service?

Keeping AI/CV functionality in a dedicated service provides a clean service boundary:

ERP Business Logic
       │
       │ HTTP
       ▼
AI / Computer Vision

This makes future ML model integration easier without tightly coupling ML dependencies to the main Java application.

📌 Current Limitations

VisionERP is an evolving portfolio project. Some components are intentionally implemented as extensible foundations rather than fully productionized systems.

Current limitations include:

AI Assistant currently uses deterministic intent routing rather than a live LLM.
Computer vision currently performs face detection and image analysis, not biometric identity recognition.
Some analytics trend values are demonstration values until sufficient historical data is accumulated.
Production deployment requires additional infrastructure and security hardening.
The current attendance workflow focuses on check-in; a complete checkout workflow can be extended further.

These limitations are documented intentionally rather than presenting prototype functionality as production capability.

🚧 Roadmap

Planned improvements include:

 Integrate an LLM-powered enterprise assistant
 Expand AI tool/function calling
 Add richer historical analytics
 Add attendance checkout workflows
 Add advanced employee search and filtering
 Expand notification/event workflows
 Add comprehensive automated integration tests
 Improve observability and monitoring
 Add production deployment configuration
 Add stronger computer-vision workflows where appropriate
 Improve frontend component modularity
🎯 What This Project Demonstrates

VisionERP is intended to demonstrate practical software-engineering skills across the complete application stack:

Frontend
   ↓
REST APIs
   ↓
Authentication & RBAC
   ↓
Business Logic
   ↓
Database
   ↓
AI / Computer Vision
   ↓
Real-Time Communication
   ↓
Auditing
   ↓
CI / DevOps

The project combines software engineering, backend development, database design, security, AI integration, API design, and DevOps practices in one cohesive application.

🤝 Contributing

Contributions, suggestions, and improvements are welcome.

Fork the repository
Create a feature branch
git checkout -b feature/your-feature
Make your changes
Run the relevant tests/builds
Commit your changes
git commit -m "Add your feature"
Push the branch
git push origin feature/your-feature
Open a Pull Request

Please see CONTRIBUTING.md for additional guidelines.

📄 License

This project is distributed under the license included in the repository.

See LICENSE for details.

**👩‍💻 Author**

Anushka Bharti

B.Tech CSE (AI & ML)
Software Engineering | Java | AI/ML | Backend Development

GitHub:
https://github.com/AnushkaBharti2

⭐ If you find this project interesting

Consider starring the repository and exploring the implementation.

VisionERP — Enterprise management with a foundation for AI-powered workflows.
