# Deployment Checklist

Before a public deployment:

- [ ] Replace every local secret with a secret-manager/environment variable.
- [ ] Use HTTPS for frontend, API and WebSocket traffic.
- [ ] Use a managed PostgreSQL instance with backups.
- [ ] Disable development seed data.
- [ ] Configure a production CORS allow-list.
- [ ] Restrict Actuator endpoints.
- [ ] Configure application and infrastructure logging.
- [ ] Add database migration backups/rollback procedures.
- [ ] Verify file-upload limits and content validation.
- [ ] Run dependency and secret scans in CI.
- [ ] Validate computer-vision performance and privacy controls before enabling biometric attendance.
