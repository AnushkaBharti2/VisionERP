package com.visionerp.entity;
import jakarta.persistence.*; import java.time.*;
@Entity @Table(name="attendance",uniqueConstraints=@UniqueConstraint(columnNames={"employee_id","work_date"})) public class Attendance { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @ManyToOne(optional=false,fetch=FetchType.EAGER) public Employee employee; @Column(name="work_date",nullable=false) public LocalDate workDate; public LocalDateTime checkIn; public LocalDateTime checkOut; public String status; public String source; public long workMinutes; }
