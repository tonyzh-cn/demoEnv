package com.example.demo.java.net;

import org.springframework.util.Assert;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileRequestEntity {
    //sysuploaddirconfig配置
    private String type;

    private Map<String,InputStream> files;

    public FileRequestEntity(Builder builder) {
        this.type = builder.type;
        this.files = builder.files;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String type;
        private Map<String,InputStream> files = new HashMap<>();

        public Builder type(String type){
            this.type = type;
            return this;
        }

        public Builder addFile(String fileName,InputStream is){
            this.files.put(fileName,is);
            return this;
        }

        public Builder files(Map<String,InputStream> files){
            this.files = files;
            return this;
        }

        public FileRequestEntity build(){
            Assert.notNull(this.type,"Type is required.");
            Assert.notEmpty(this.files,"Files can not be empty.");

            return new FileRequestEntity(this);
        }

    }

    public String getType() {
        return type;
    }

    public Map<String, InputStream> getFiles() {
        return files;
    }
}
