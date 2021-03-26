package com.example.demo.java.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.*;

public class SolrDemo {
    public static void main(String[] args) throws IOException, SolrServerException {
        HttpSolrClient client = new HttpSolrClient.Builder("http://127.0.0.1:8218/hljzyydx-solr/activiti").build();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("businessKey_s:8$$1$$2@3068");
        solrQuery.setSort("activiti_createTime_s", SolrQuery.ORDER.desc);
        QueryResponse response = client.query(solrQuery, SolrRequest.METHOD.POST);
        SolrDocumentList documents = response.getResults();
        for(SolrDocument doc : documents){
            List<String> sortedFields = new ArrayList<>(doc.keySet());
            Collections.sort(sortedFields);
            for(String field : sortedFields){
                System.out.println(field+"<=>"+doc.get(field));
            }

            System.out.println("============================================================================================");
        }

    }
}
