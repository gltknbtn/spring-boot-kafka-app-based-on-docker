package com.gltknbtn.repository.solr;

import com.gltknbtn.model.solr.InputOutputLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface InputOutputLogSolrRepository extends SolrCrudRepository<InputOutputLog, String> {
 
    @Query("id:*?0* OR name:*?0*")
    public Page<InputOutputLog> findByCustomQuery(String searchTerm, Pageable pageable);
 
    @Query(name = "Product.findByNamedQuery")
    public Page<InputOutputLog> findByNamedQuery(String searchTerm, Pageable pageable);

    List<InputOutputLog> findByMessageLike(String reqMessage);
 
}