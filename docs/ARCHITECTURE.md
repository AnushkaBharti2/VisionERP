# Architecture

```text
React + TypeScript
       |
       | HTTPS / REST + WebSocket
       v
Spring Boot API ---- PostgreSQL
       |
       +---- Python FastAPI AI/CV service
       |
       +---- controlled AI tools / LLM integration
```

## Core backend flow

`Controller -> DTO/validation -> Service -> Repository -> PostgreSQL`

The backend owns authentication, authorization, business rules and persistence. The Python service owns computer-vision inference. The AI assistant is tool-controlled: it can only request explicit backend operations and never executes arbitrary SQL.

## Runtime services
- `frontend`: React application
- `backend`: Spring Boot API
- `postgres`: relational persistence
- `ai-service`: Python/FastAPI CV service

## Deployment principle
Use HTTPS in production, managed PostgreSQL, secret injection, least-privilege service accounts, health checks, centralized logs and CI/CD. The local Docker Compose stack is for development/demo use.
