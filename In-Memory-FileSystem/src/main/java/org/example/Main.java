package org.example;

import org.example.entity.Directory;
import org.example.entity.File;
import org.example.repository.FileRepository;
import org.example.repository.FileRepositoryImpl;
import org.example.service.FileService;
import org.example.service.FileServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        File file1 = new File("a.txt", 12);
        File file2 = new File("b.txt", 15);
        File file3 = new File("c.txt", 15);
        File file4 = new File("d.txt", 15);

        FileRepository fileRepository = new FileRepositoryImpl();

        Directory root = fileRepository.getRootDirectory();
        root.addFile(file1);
        root.addFile(file2);

        Directory dir2 = new Directory("home");
        dir2.addFile(file3);
        dir2.addFile(file4);

        root.addDirectory(dir2);

        FileService fileService = new FileServiceImpl(root);

        List<File> fileList = fileService.getFilesBySize(15);
        for(File file : fileList) {
            System.out.println(file.getName());
            System.out.println(file.getSize());
            System.out.println(file.getCreatedAt());
            System.out.println(file.getUpdatedAt());
            System.out.println(file.getContent());

            System.out.println();
        }

    }
}