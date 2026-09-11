package com.visionerp.entity;
import jakarta.persistence.*; import java.time.*;
@Entity @Table(name="notifications") public class Notification { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @Column(nullable=false) public String recipientEmail; @Column(nullable=false) public String type; @Column(nullable=false) public String message; @Column(nullable=false) public boolean readFlag=false; public LocalDateTime createdAt=LocalDateTime.now(); }
