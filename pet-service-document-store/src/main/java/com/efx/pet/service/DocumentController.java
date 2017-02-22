package com.efx.pet.service;

import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Paths;

@Controller
public class DocumentController {

    private final GridFsTemplate gridFsTemplate;

    @Autowired
    public DocumentController(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    @GetMapping(path = "/document", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> blah() throws Exception {
        URL url = this.getClass().getClassLoader().getResource("sample.pdf");
        File file = Paths.get(url.toURI()).toFile();

        FileInputStream fileInputStream = new FileInputStream(file);

        gridFsTemplate.store(fileInputStream, "sample.pdf");

        GridFSDBFile resultFile = gridFsTemplate.findOne(
                new Query().addCriteria(Criteria.where("filename").is("sample.pdf")));

        return new ResponseEntity<>(IOUtils.toByteArray(resultFile.getInputStream()), HttpStatus.OK);
    }
}
