package com.example.attendance.web.controller;

import com.example.attendance.domain.Attendance;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.web.dto.requestDto.AttendanceRequestDTO;
import com.example.attendance.web.dto.responseDto.AttendanceResponseDTO;
import com.example.attendance.web.dto.responseDto.ResponseDto;
import com.example.attendance.web.exception.ExceptionResponse;
import javassist.NotFoundException;
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
            response.setSuccess(attendanceService.markAttendance(userId, attendanceDTO));
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
            response.setSuccess(attendanceService.updateAttendance(userId, attendanceId, attendanceDTO));
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
            List<AttendanceResponseDTO> attendances = attendanceService.getAllAttendance(userId);
            response.setSuccess(attendances);
            return ResponseEntity.ok(response);
        } catch (SecurityException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.FORBIDDEN.value(), "Access Denied", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        } catch (NotFoundException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(),
                    "There are no records present", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<ResponseDto> getAttendanceForUserID(@RequestParam Long userId) {
        ResponseDto response = new ResponseDto();
        try {
            List<AttendanceResponseDTO> attendances = attendanceService.getAttendanceForUserID(userId);
            response.setSuccess(attendances);
            return ResponseEntity.ok(response);
        } catch (SecurityException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.FORBIDDEN.value(), "Access Denied", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        } catch (NotFoundException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(),
                    "There are no records present", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
