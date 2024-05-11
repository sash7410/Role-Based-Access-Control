package com.example.attendance.web.dto.requestDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequestDTO {
    private LocalDate date;
    private boolean status;
}

