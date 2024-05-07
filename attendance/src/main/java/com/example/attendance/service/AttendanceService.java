package com.example.attendance.service;

import com.example.attendance.domain.Attendance;
import com.example.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SecurityMiddleware securityMiddleware;

    @Transactional
    public Attendance markAttendance(Long userId, Attendance attendance) {
        if (!securityMiddleware.hasPermission(userId, "mark_attendance")) {
            throw new SecurityException("Access Denied: Insufficient Permissions");
        }
        return attendanceRepository.save(attendance);
    }

    @Transactional
    public Optional<Attendance> updateAttendance(Long userId, Long attendanceId, Attendance newAttendance) {
        if (!securityMiddleware.hasPermission(userId, "update_attendance")) {
            throw new SecurityException("Access Denied: Insufficient Permissions");
        }
        return attendanceRepository.findById(attendanceId)
                .map(attendance -> {
                    attendance.setDate(newAttendance.getDate());
                    attendance.setStatus(newAttendance.isStatus());
                    return attendanceRepository.save(attendance);
                });
    }

    public List<Attendance> getAllAttendance(Long userId) {
        if (!securityMiddleware.hasPermission(userId, "view_attendance")) {
            throw new SecurityException("Access Denied: Insufficient Permissions");
        }
        return attendanceRepository.findAll();
    }


}
