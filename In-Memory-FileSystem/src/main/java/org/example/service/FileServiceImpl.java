package org.example.service;

import org.example.entity.Directory;
import org.example.entity.File;

import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService{
    private Directory root;

    public FileServiceImpl(Directory root) {
        this.root = root;
    }

    @Override
    public List<File> getFilesBySize(int size) {
        List<File> filteredFiles = new ArrayList<>();
        getFilesBySizeRecursive(root, size, filteredFiles);
        return filteredFiles;
    }

    private void getFilesBySizeRecursive(Directory root, int size, List<File> filteredFiles) {
        for(File file : root.getFileList()) {
            if(file.getSize() == size) {
                filteredFiles.add(file);
            }
        }
        for(Directory subDirectory : root.getDirectoryList()) {
            getFilesBySizeRecursive(subDirectory, size, filteredFiles);
        }
    }
}
