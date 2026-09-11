package com.visionerp.controller;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.*; import java.util.*;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,Object>> bad(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("error","bad_request","message",e.getMessage()));}
 @ExceptionHandler(NoSuchElementException.class) ResponseEntity<Map<String,Object>> missing(NoSuchElementException e){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","not_found"));}
}
