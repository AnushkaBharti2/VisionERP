package com.visionerp.controller;

import com.visionerp.entity.*; import com.visionerp.repository.*; import com.visionerp.service.AuditService;
import jakarta.validation.Valid; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/employees")
public class EmployeeController {
 private final EmployeeRepository r; private final DepartmentRepository d; private final AuditService audit;
 public EmployeeController(EmployeeRepository r,DepartmentRepository d,AuditService a){this.r=r;this.d=d;this.audit=a;}
 @GetMapping public List<Employee> all(){return r.findAll();}
 @GetMapping("/{id}") public Employee one(@PathVariable Long id){return r.findById(id).orElseThrow();}
 @PostMapping @PreAuthorize("hasAnyRole('ADMIN','HR')")
 public Employee create(@RequestBody Map<String,Object>b,org.springframework.security.core.Authentication a){
  Employee e=new Employee(); e.employeeCode=req(b,"employeeCode"); e.firstName=req(b,"firstName"); e.lastName=req(b,"lastName"); e.email=req(b,"email");
  e.phone=(String)b.get("phone"); e.designation=(String)b.get("designation"); e.role=Role.valueOf((String)b.getOrDefault("role","EMPLOYEE"));
  if(b.get("departmentId")!=null)e.department=d.findById(Long.valueOf(b.get("departmentId").toString())).orElseThrow();
  Employee saved=r.save(e); audit.log(a.getName(),"CREATE","EMPLOYEE",saved.id.toString(),saved.email); return saved;
 }
 @PutMapping("/{id}") @PreAuthorize("hasAnyRole('ADMIN','HR')")
 public Employee update(@PathVariable Long id,@RequestBody Map<String,Object>b,org.springframework.security.core.Authentication a){
  Employee e=r.findById(id).orElseThrow(); if(b.containsKey("firstName"))e.firstName=(String)b.get("firstName"); if(b.containsKey("lastName"))e.lastName=(String)b.get("lastName");
  if(b.containsKey("phone"))e.phone=(String)b.get("phone"); if(b.containsKey("designation"))e.designation=(String)b.get("designation");
  if(b.containsKey("active"))e.active=Boolean.parseBoolean(b.get("active").toString()); if(b.get("departmentId")!=null)e.department=d.findById(Long.valueOf(b.get("departmentId").toString())).orElseThrow();
  if(b.containsKey("role"))e.role=Role.valueOf(b.get("role").toString()); Employee saved=r.save(e); audit.log(a.getName(),"UPDATE","EMPLOYEE",id.toString(),e.email); return saved;
 }
 @DeleteMapping("/{id}") @PreAuthorize("hasAnyRole('ADMIN','HR')") public void deactivate(@PathVariable Long id,org.springframework.security.core.Authentication a){Employee e=r.findById(id).orElseThrow();e.active=false;r.save(e);audit.log(a.getName(),"DEACTIVATE","EMPLOYEE",id.toString(),e.email);}
 private String req(Map<String,Object>b,String k){Object v=b.get(k); if(v==null||v.toString().isBlank())throw new IllegalArgumentException(k+" is required"); return v.toString();}
}
