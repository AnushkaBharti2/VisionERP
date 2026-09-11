# API Overview

Base URL: `/api`

## Authentication
- `POST /auth/login`

## Employees
- `GET /employees`
- `GET /employees/{id}`
- `POST /employees` — ADMIN/HR
- `PUT /employees/{id}` — ADMIN/HR
- `DELETE /employees/{id}` — ADMIN/HR (soft deactivation)

## Departments
- `GET /departments`
- `POST /departments` — ADMIN/HR
- `PUT /departments/{id}` — ADMIN/HR
- `DELETE /departments/{id}` — ADMIN

## Attendance
- `GET /attendance/today`
- `GET /attendance/employee/{id}`
- `POST /attendance/check-in/{employeeId}`
- `POST /attendance/check-out/{employeeId}`

## Visitors
- `GET /visitors`
- `POST /visitors` — ADMIN/HR/SECURITY
- `PATCH /visitors/{id}/status?value=...`

## Notifications
- `GET /notifications`
- `PATCH /notifications/{id}/read`

## Analytics / assistant / audit
- `GET /dashboard/summary`
- `POST /assistant/ask`
- `GET /audit` — ADMIN

Swagger UI: `/swagger-ui.html`
OpenAPI JSON: `/v3/api-docs`
