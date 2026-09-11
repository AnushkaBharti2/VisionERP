package com.visionerp.entity;
import jakarta.persistence.*; import java.time.*;
@Entity @Table(name="audit_logs") public class AuditLog { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; public String actorEmail; public String action; public String entityType; public String entityId; @Column(length=2000) public String details; public LocalDateTime createdAt=LocalDateTime.now(); }
