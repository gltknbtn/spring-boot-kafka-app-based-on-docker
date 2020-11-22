package com.gltknbtn.samplebootapp;

import com.gltknbtn.SolrConfig;
import com.gltknbtn.model.solr.InputOutputLog;
import com.gltknbtn.repository.solr.InputOutputLogSolrRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SolrConfig.class)
public class InputOutputLogSolrRepositoryTest {

    @Autowired private InputOutputLogSolrRepository inputOutputLogSolrRepository;

    @Test
    public void testFindByMessage(){
        List<InputOutputLog> list = inputOutputLogSolrRepository.findByMessageLike("gültekin");
        System.out.println("list: " + list);
        assertEquals(72, list.size());
    }

}
