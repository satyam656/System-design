package org.example.service;

import org.example.entity.File;

import java.util.List;

public interface FileService {
    public List<File> getFilesBySize(int size);

}
