package com.gltknbtn.model.mongo;

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
public class InputOutputLog {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private LogDirection logDirection;

    private String correlationId;

    private LocalDateTime createdDate;

    @Column(length = 1000)
    private String message;

    private Integer responseStatus;

    private Long executionTime; //ms

}