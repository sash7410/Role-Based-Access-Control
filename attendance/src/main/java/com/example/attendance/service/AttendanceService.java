package com.example.attendance.service;

import com.example.attendance.domain.Attendance;
import com.example.attendance.repository.AttendanceRepository;
import com.example.attendance.web.dto.requestDto.AttendanceRequestDTO;
import com.example.attendance.web.dto.responseDto.AttendanceResponseDTO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SecurityMiddleware securityMiddleware;
    @Autowired
    private UserRoleService userRoleService;

    @Transactional
    public AttendanceResponseDTO markAttendance(Long userId, AttendanceRequestDTO attendanceDTO) {
        if (!securityMiddleware.hasPermission(userId, "mark_attendance")) {
            throw new SecurityException("Access Denied: Insufficient Permissions");
        }
        Attendance attendance = Attendance.builder().userId(userId).date(attendanceDTO.getDate())
                .status(attendanceDTO.isStatus()).build();
        attendanceRepository.save(attendance);
        return getAttendanceResponseDto(attendance);
    }

    @Transactional
    public AttendanceResponseDTO updateAttendance(Long userId, Long attendanceId, AttendanceRequestDTO attendanceDTO) {
        if (!securityMiddleware.hasPermission(userId, "update_attendance")) {
            throw new SecurityException("Access Denied: Insufficient Permissions");
        }
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new IllegalArgumentException("Attendance not found"));

        attendance.setDate(attendanceDTO.getDate());
        attendance.setStatus(attendanceDTO.isStatus());
        attendanceRepository.save(attendance);
        return getAttendanceResponseDto(attendance);
    }

    public List<AttendanceResponseDTO> getAllAttendance(Long userId) throws NotFoundException {
        if (!securityMiddleware.hasPermission(userId, "list_attendance")) {
            throw new SecurityException("Access Denied: Insufficient Permissions");
        }
        return getAttendanceResponseDTOS(attendanceRepository.findAll());
    }
    public List<AttendanceResponseDTO> getAttendanceForUserID(Long userId) throws NotFoundException {
        if (!securityMiddleware.hasPermission(userId, "view_attendance")) {
            throw new SecurityException("Access Denied: Insufficient Permissions");
        }
        return getAttendanceResponseDTOS(attendanceRepository.findByUserId(userId));
    }

    private List<AttendanceResponseDTO> getAttendanceResponseDTOS(List<Attendance> attendanceList) throws NotFoundException {
        List<AttendanceResponseDTO> attendanceResponseDTOList = new ArrayList<>();
        for (Attendance attendance : attendanceList) {
            attendanceResponseDTOList.add(getAttendanceResponseDto(attendance));
        }
        if(attendanceResponseDTOList.isEmpty()){
            throw new NotFoundException("Access Denied: Insufficient Permissions");

        }
        return attendanceResponseDTOList;
    }

    private AttendanceResponseDTO getAttendanceResponseDto(Attendance attendance) {
        return AttendanceResponseDTO.builder()
                .attendanceId(attendance.getAttendanceId())
                .userId(attendance.getUserId())
                .status(attendance.isStatus())
                .date(attendance.getDate())
                .userName(userRoleService.getUserName(attendance.getUserId())).build();
    }
}