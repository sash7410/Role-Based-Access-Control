package com.example.attendance.service;

import com.example.attendance.domain.Attendance;
import com.example.attendance.repository.AttendanceRepository;
import com.example.attendance.web.dto.requestDto.AttendanceRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SecurityMiddleware securityMiddleware;

    @Transactional
    public Attendance markAttendance(Long userId, AttendanceRequestDTO attendanceDTO) {
        if (!securityMiddleware.hasPermission(userId, "mark_attendance")) {
            throw new SecurityException("Access Denied: Insufficient Permissions");
        }
        Attendance attendance = Attendance.builder().userId(userId).date(attendanceDTO.getDate()).status(attendanceDTO.isStatus()).build();
        return attendanceRepository.save(attendance);
    }

    @Transactional
    public Attendance updateAttendance(Long userId, Long attendanceId, AttendanceRequestDTO attendanceDTO) {
        if (!securityMiddleware.hasPermission(userId, "update_attendance")) {
            throw new SecurityException("Access Denied: Insufficient Permissions");
        }
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new IllegalArgumentException("Attendance not found"));

        attendance.setDate(attendanceDTO.getDate());
        attendance.setStatus(attendanceDTO.isStatus());
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance(Long userId) {
        if (!securityMiddleware.hasPermission(userId, "view_attendance")) {
            throw new SecurityException("Access Denied: Insufficient Permissions");
        }
        return attendanceRepository.findAll();
    }
}