package com.gltknbtn.model.solr;

import com.gltknbtn.enums.LogDirection;
import lombok.Data;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@SolrDocument(collection = "InputOutputLog")
@Data
public class InputOutputLog {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "logDirection", type = "string")
    @Enumerated(EnumType.STRING)
    private LogDirection logDirection;

    @Indexed(name = "correlationId", type = "string")
    private String correlationId;

    @Indexed(name = "createdDate", type = "string")
    private LocalDateTime createdDate;

    @Indexed(name = "message", type = "string")
    private String message;

    @Indexed(name = "responseStatus", type = "integer")
    private Integer responseStatus;

    @Indexed(name = "executionTime", type = "long")
    private Long executionTime; //ms

}