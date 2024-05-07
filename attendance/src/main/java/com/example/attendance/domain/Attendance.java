package com.example.attendance.domain;

import com.example.attendance.constant.Constants;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private Long id;
    private Long userId;
    private Date date;
    private boolean status;
}
