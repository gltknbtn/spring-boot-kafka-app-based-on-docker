package com.gltknbtn.model.mysql;

import com.gltknbtn.enums.LogDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // This tells Hibernate to make a table out of this class
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(schema = "test", name = "input_output_log")
public class InputOutputLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "log_direction")
    private LogDirection logDirection;

    @Column(name = "correlation_id")
    private String correlationId;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "message", length = 1000)
    private String message;

    @Column(name = "response_status")
    private Integer responseStatus;

    @Column(name = "execution_time")
    private Long executionTime; //ms

}