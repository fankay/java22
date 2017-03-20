package com.kaishengit.action;

import java.io.*;

public class FileAction extends BaseAction{

    private String uName;
    private File[] doc;
    private String[] docFileName;
    private String[] docContentType;
    //文件下载使用
    private String fileName;
    private Long size;
    private String fileContentType;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String upload() throws Exception {
        System.out.println("uName:" + uName);
        for(int i = 0;i < doc.length;i++) {
            System.out.println("FileName:" + docFileName[i]);
            System.out.println("ContentType:" + docContentType[i]);

            //InputStream inputStream = new FileInputStream(doc[i]);
        }
        //...

        return SUCCESS;
    }


    public String download() throws UnsupportedEncodingException {
        fileName = new String("书籍封面.jpg".getBytes("UTF-8"),"ISO8859-1");
        size = new File("D:/upload/0a93153c-ce40-4355-9566-19219cd637d2.jpg").length();
        fileContentType = "image/jpg";
        return SUCCESS;
    }

    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(new File("D:/upload/0a93153c-ce40-4355-9566-19219cd637d2.jpg"));
    }

    //get set

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public File[] getDoc() {
        return doc;
    }

    public void setDoc(File[] doc) {
        this.doc = doc;
    }

    public String[] getDocFileName() {
        return docFileName;
    }

    public void setDocFileName(String[] docFileName) {
        this.docFileName = docFileName;
    }

    public String[] getDocContentType() {
        return docContentType;
    }

    public void setDocContentType(String[] docContentType) {
        this.docContentType = docContentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
}
