package com.visionerp.config;

import com.visionerp.entity.*;
import com.visionerp.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;

@Configuration
public class DataInitializer {
    @Value("${visionerp.admin.email:}") private String adminEmail;
    @Value("${visionerp.admin.password:}") private String adminPassword;

    @Bean CommandLineRunner seed(UserRepository users, DepartmentRepository deps, EmployeeRepository emps, PasswordEncoder enc) {
        return args -> {
            if (!adminEmail.isBlank() && !adminPassword.isBlank() && users.findByEmail(adminEmail).isEmpty()) {
                User u = new User(); u.email = adminEmail; u.passwordHash = enc.encode(adminPassword); u.role = Role.ADMIN; users.save(u);
            }
            if (deps.count() == 0) {
                deps.save(dept("Engineering", "Software and AI engineering"));
                deps.save(dept("Human Resources", "People operations"));
                deps.save(dept("Security", "Physical and workplace security"));
            }
            if (emps.count() == 0 && deps.count() > 0) {
                Department d = deps.findAll().get(0);
                Employee e = new Employee(); e.employeeCode="EMP001"; e.firstName="Demo"; e.lastName="Employee";
                e.email="employee@visionerp.local"; e.designation="Software Engineer"; e.department=d; e.role=Role.EMPLOYEE;
                e.joiningDate=LocalDate.now().minusMonths(6); emps.save(e);
            }
        };
    }
    private Department dept(String name, String description) { Department d=new Department(); d.name=name; d.description=description; return d; }
}
