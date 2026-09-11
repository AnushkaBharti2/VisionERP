package com.visionerp.entity;
import jakarta.persistence.*;
@Entity @Table(name="users") public class User { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @Column(nullable=false,unique=true) public String email; @Column(nullable=false) public String passwordHash; @Enumerated(EnumType.STRING) @Column(nullable=false) public Role role=Role.EMPLOYEE; @Column(nullable=false) public boolean enabled=true; }
