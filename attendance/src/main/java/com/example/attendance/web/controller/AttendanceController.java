package com.example.attendance.web.controller;

import com.example.attendance.domain.Attendance;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.web.dto.requestDto.AttendanceRequestDTO;
import com.example.attendance.web.dto.responseDto.ResponseDto;
import com.example.attendance.web.exception.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    public ResponseEntity<ResponseDto> markAttendance(@RequestBody AttendanceRequestDTO attendanceDTO, @RequestParam Long userId) {
        ResponseDto response = new ResponseDto();
        try {
            Attendance createdAttendance = attendanceService.markAttendance(userId, attendanceDTO);
            response.setSuccess(createdAttendance);
            return ResponseEntity.ok(response);
        } catch (SecurityException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.FORBIDDEN.value(), "Access Denied", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @PutMapping("/{attendanceId}")
    public ResponseEntity<ResponseDto> updateAttendance(@PathVariable Long attendanceId, @RequestBody AttendanceRequestDTO attendanceDTO, @RequestParam Long userId) {
        ResponseDto response = new ResponseDto();
        try {
            Attendance updatedAttendance = attendanceService.updateAttendance(userId, attendanceId, attendanceDTO);
            response.setSuccess(updatedAttendance);
            return ResponseEntity.ok(response);
        } catch (SecurityException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.FORBIDDEN.value(), "Access Denied", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllAttendance(@RequestParam Long userId) {
        ResponseDto response = new ResponseDto();
        try {
            List<Attendance> attendances = attendanceService.getAllAttendance(userId);
            response.setSuccess(attendances);
            return ResponseEntity.ok(response);
        } catch (SecurityException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.FORBIDDEN.value(), "Access Denied", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }
}
