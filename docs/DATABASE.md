# Database

The first Flyway migration creates the core tables:

`users`, `departments`, `employees`, `attendance`, `visitors`, `notifications`, `audit_logs`.

Important constraints include unique employee codes/emails and one attendance row per employee per work date.

Future migration targets include biometric templates, refresh tokens, visitor event history, notification delivery state, and richer analytics aggregates.
