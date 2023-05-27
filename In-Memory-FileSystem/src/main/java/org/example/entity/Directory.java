package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    private String name;
    private int size;
    private long createdAt;
    private long updatedAt;
    private List<File> fileList;
    private List<Directory> directoryList;

    public Directory(String name) {
        this.name = name;
        this.size = 0;
        this.fileList = new ArrayList<File>();
        this.directoryList = new ArrayList<Directory>();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.updatedAt = System.currentTimeMillis();
        this.name = name;
    }

    public int getSize() {
        return this.size;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void addFile(File file) {
        this.size += file.getSize();
        this.fileList.add(file);
        this.updatedAt = System.currentTimeMillis();
    }

    public void removeFile(File file) {
        size -= file.getSize();
        this.fileList.remove(file);
        this.updatedAt = System.currentTimeMillis();
    }
    public List<File> getFileList() {
        return this.fileList;
    }
    public void addDirectory(Directory directory) {
        this.size += directory.getSize();
        this.directoryList.add(directory);
        this.updatedAt = System.currentTimeMillis();
    }

    public void removeDirectory(Directory directory) {
        this.size -= directory.getSize();
        this.directoryList.remove(directory);
        this.updatedAt = System.currentTimeMillis();
    }

    public List<Directory> getDirectoryList() {
        return this.directoryList;
    }
}
