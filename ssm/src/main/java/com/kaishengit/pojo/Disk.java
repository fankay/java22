package com.kaishengit.pojo;

import lombok.Data;

@Data
public class Disk {
    public static final String FILE_TYPE = "file";
    public static final String DIRECTORY_TYPE = "directory";

    private Integer id;
    private String name;
    private String sourceName;
    private Integer fid;
    private String size;
    private String createTime;
    private String createUser;
    private String type;
}
