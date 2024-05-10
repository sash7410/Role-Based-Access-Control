package com.example.attendance.domain;

import com.example.attendance.constant.Constants;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Simple JavaBean domain object representing an Attendance Object.
 *
 * @author Sashank RM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = Constants.ATTENDANCE_TABLE_NAME)
@EntityListeners(AuditingEntityListener.class)
public class Attendance {
    public static final String ATTENDANCE_TABLE_NAME = "ATTENDANCE_TABLE";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attendanceId;
    private Long userId;
    private LocalDate date;
    private boolean status;
}
