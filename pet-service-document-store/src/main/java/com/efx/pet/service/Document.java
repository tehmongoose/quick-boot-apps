package com.efx.pet.service;

import org.springframework.data.annotation.Id;

import java.io.FileInputStream;

public class Document {

	@Id String id;
    private FileInputStream file;


    public void setFile(FileInputStream file) {
        this.file = file;
    }

    public FileInputStream getFile() {
        return file;
    }
}