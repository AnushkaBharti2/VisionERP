# VisionERP Roadmap

## Implemented v1
- JWT auth and role model
- Employee and department management
- Attendance check-in/out
- Visitor lifecycle
- Notifications persistence + WebSocket foundation
- Dashboard and analytics UI
- Controlled assistant tool endpoint
- Audit log
- Python CV analysis endpoint
- Dockerized frontend/backend/AI/Postgres

## Next production-grade iterations
1. Automated integration tests and Testcontainers.
2. OpenAPI/Swagger documentation.
3. Refresh-token rotation and account recovery.
4. Object storage for visitor/employee media with signed URLs.
5. Consent and biometric retention/deletion workflows.
6. Liveness/anti-spoofing and validated face-embedding recognition service.
7. Historical analytics and exportable reports.
8. Queue/event bus if scale requires it.
9. Observability: metrics, traces, structured logs.
10. CI/CD with security scanning and container image scanning.
