package com.example.demo.java.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data implements Serializable {

    public final static long serialVersionUID=1L;

    @JsonProperty(value = "file_type")
    private String fileType;
    @JsonProperty(value = "file_path")
    private String filePath;

    /**
     get、set方法省略
     **/

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
