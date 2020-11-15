package com.gltknbtn;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(
        basePackages = "com.gltknbtn.repository.solr",
        namedQueriesLocation = "classpath:solr-named-queries.properties")
@ComponentScan
public class SolrConfig {

    @Autowired
    private Environment env;

    @Bean
    public SolrClient solrClient() {
        SolrClient solrClient = new HttpSolrClient.Builder("http://" +env.getProperty("SOLR_HOSTNAME") +":8983/solr").build();
        return solrClient;
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}