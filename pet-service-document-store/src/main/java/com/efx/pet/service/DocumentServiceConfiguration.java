package com.efx.pet.service;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@Configuration
public class DocumentServiceConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "document-demo";
    }

    @Override
    public MongoClient mongo() {
        return new Fongo("InMemoryMongo").getMongo();
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

}
