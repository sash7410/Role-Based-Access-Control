package com.example.attendance.web.controller;

import com.example.attendance.domain.Attendance;
import com.example.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Exposes all Attendance - RESTful web services
 *
 * @author Sashank RM
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<?> markAttendance(@RequestBody Attendance attendance, @RequestParam Long userId) {
        try {
            Attendance createdAttendance = attendanceService.markAttendance(userId, attendance);
            return ResponseEntity.ok(createdAttendance);
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }

    @PutMapping("/{attendanceId}")
    public ResponseEntity<?> updateAttendance(@PathVariable Long attendanceId, @RequestBody Attendance attendance, @RequestParam Long userId) {
        try {
            Optional<Attendance> updatedAttendance = attendanceService.updateAttendance(userId, attendanceId, attendance);
            return updatedAttendance.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAttendance(@RequestParam Long userId) {
        try {
            List<Attendance> attendances = attendanceService.getAllAttendance(userId);
            return ResponseEntity.ok(attendances);
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }
}