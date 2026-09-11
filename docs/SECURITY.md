# Security Model

VisionERP treats authentication, authorization, auditability and data minimization as first-class concerns.

## Authentication
- Passwords are stored as BCrypt hashes.
- API access uses stateless JWT authentication.
- Secrets are supplied through environment variables.

## Authorization
- Roles: ADMIN, HR, SECURITY, EMPLOYEE.
- Spring Security and method-level `@PreAuthorize` checks protect privileged endpoints.
- Visitor status changes are restricted to privileged staff or the visitor's host.
- Notification read operations verify recipient ownership.

## AI security
The AI assistant must never receive arbitrary SQL access. It should invoke a small allow-list of backend tools, and the backend enforces the requesting user's role before executing each tool.

## Biometric privacy
The computer-vision subsystem should collect biometric data only with an explicit lawful purpose and consent where required. Store the minimum necessary representation, restrict access, define retention/deletion policies, and provide a revocation path. Do not claim production biometric identification without validation.

## Public-repository rules
Never commit `.env`, credentials, private keys, API keys, raw employee/visitor data, biometric images, or database dumps.
