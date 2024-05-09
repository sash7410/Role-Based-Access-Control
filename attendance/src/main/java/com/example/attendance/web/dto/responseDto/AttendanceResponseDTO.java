package com.example.attendance.web.dto.responseDto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceResponseDTO {
    private Long attendanceId;
    private Long userId;
    private String userName;
    private LocalDate date;
    private boolean status;
}
