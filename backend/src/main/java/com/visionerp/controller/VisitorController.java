package com.visionerp.controller;

import com.visionerp.entity.*; import com.visionerp.repository.*; import com.visionerp.service.*;
import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.time.*; import java.util.*;

@RestController @RequestMapping("/api/visitors")
public class VisitorController {
 private final VisitorRepository r; private final EmployeeRepository e; private final NotificationService n; private final AuditService audit;
 public VisitorController(VisitorRepository r,EmployeeRepository e,NotificationService n,AuditService a){this.r=r;this.e=e;this.n=n;this.audit=a;}
 @GetMapping public List<Visitor> all(){return r.findAllByOrderByCreatedAtDesc();}
 @PostMapping @PreAuthorize("hasAnyRole('ADMIN','HR','SECURITY')")
 public Visitor create(@RequestBody Map<String,Object>b,org.springframework.security.core.Authentication a){
  String name=String.valueOf(b.getOrDefault("name","")).trim(); if(name.isBlank())throw new IllegalArgumentException("Visitor name is required");
  Visitor v=new Visitor();v.name=name;v.phone=(String)b.get("phone");v.email=(String)b.get("email");v.purpose=(String)b.get("purpose");
  if(b.get("hostId")!=null)v.host=e.findById(Long.valueOf(b.get("hostId").toString())).orElseThrow();
  Visitor s=r.save(v); if(s.host!=null)n.send(s.host.email,"VISITOR","Visitor "+s.name+" is waiting for approval."); audit.log(a.getName(),"CREATE","VISITOR",s.id.toString(),s.name);return s;
 }
 @PatchMapping("/{id}/status")
 public Visitor status(@PathVariable Long id,@RequestParam String value,org.springframework.security.core.Authentication a){
  Visitor v=r.findById(id).orElseThrow(); String role=a.getAuthorities().stream().findFirst().map(x->x.getAuthority()).orElse("");
  Set<String> allowed=Set.of("WAITING","APPROVED","REJECTED","CHECKED_IN","CHECKED_OUT"); if(!allowed.contains(value))throw new IllegalArgumentException("Unsupported visitor status");
  boolean host=v.host!=null&&v.host.email.equalsIgnoreCase(a.getName()); boolean privileged=role.equals("ROLE_ADMIN")||role.equals("ROLE_HR")||role.equals("ROLE_SECURITY");
  if(!privileged&&!host)throw new org.springframework.security.access.AccessDeniedException("Not allowed to update this visitor");
  v.status=value;if("CHECKED_IN".equals(value))v.checkInTime=LocalDateTime.now();if("CHECKED_OUT".equals(value))v.checkOutTime=LocalDateTime.now();
  Visitor s=r.save(v);audit.log(a.getName(),"STATUS","VISITOR",id.toString(),value);return s;
 }
}
