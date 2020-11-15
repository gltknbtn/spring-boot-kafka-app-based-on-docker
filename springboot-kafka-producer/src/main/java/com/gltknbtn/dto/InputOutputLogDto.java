package com.gltknbtn.dto;

import com.gltknbtn.enums.LogDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputOutputLogDto {
    private String id;
    private LogDirection logDirection;
    private String correlationId;
    private LocalDateTime createdDate;
    private String message;
    private Integer responseStatus;
    private Long executionTime; //ms
}