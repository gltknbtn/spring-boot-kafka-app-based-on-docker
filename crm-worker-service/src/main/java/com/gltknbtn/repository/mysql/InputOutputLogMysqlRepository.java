package com.gltknbtn.repository.mysql;

import com.gltknbtn.model.mongo.InputOutputLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InputOutputLogMysqlRepository extends JpaRepository<com.gltknbtn.model.mysql.InputOutputLog, Long> {

    public List<InputOutputLog> findByCorrelationId(String correlationId);

}