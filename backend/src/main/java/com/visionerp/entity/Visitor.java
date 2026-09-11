package com.visionerp.entity;
import jakarta.persistence.*; import java.time.*;
@Entity @Table(name="visitors") public class Visitor { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @Column(nullable=false) public String name; public String phone; public String email; public String purpose; public String photoUrl; @ManyToOne(fetch=FetchType.EAGER) public Employee host; public String status="WAITING"; public LocalDateTime checkInTime; public LocalDateTime checkOutTime; public LocalDateTime createdAt=LocalDateTime.now(); }
