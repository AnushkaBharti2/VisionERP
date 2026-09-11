package com.visionerp.controller;
import com.visionerp.repository.NotificationRepository; import org.springframework.security.access.AccessDeniedException; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/notifications") public class NotificationController { private final NotificationRepository r; public NotificationController(NotificationRepository r){this.r=r;}
 @GetMapping public Object all(org.springframework.security.core.Authentication a){return r.findTop30ByRecipientEmailOrderByCreatedAtDesc(a.getName());}
 @PatchMapping("/{id}/read") public Object read(@PathVariable Long id,org.springframework.security.core.Authentication a){var n=r.findById(id).orElseThrow();if(!n.recipientEmail.equalsIgnoreCase(a.getName()))throw new AccessDeniedException("Not allowed");n.readFlag=true;return r.save(n);}
}
