package org.example.repository;

import org.example.entity.Directory;

public class FileRepositoryImpl implements FileRepository{
    private Directory root;

    public FileRepositoryImpl() {
        this.root = new Directory("/");
    }

    @Override
    public Directory getRootDirectory() {
        return this.root;
    }
}
