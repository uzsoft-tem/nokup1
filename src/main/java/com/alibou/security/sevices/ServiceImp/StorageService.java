package com.alibou.security.sevices.ServiceImp;


import com.alibou.security.repository.FileDataRepository;
import com.alibou.security.repository.MasterImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StorageService {

    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private MasterImagesRepository masterImagesRepository;

    private final String FOLDER_PATH= System.getProperty("user.dir") + "\\uploadFiles\\UsersImages\\";
    private final String FOLDER_PATH_Master= System.getProperty("user.dir") + "\\uploadFiles\\MasterImages\\";

    public String generateImageName(){
        String uuid= String.valueOf(UUID.randomUUID());
        boolean b = fileDataRepository.existsByOriginalNameNot(String.valueOf(uuid));
        return b?uuid:generateImageName();
    }

//    public UserImages uploadImageToFileSystem(MultipartFile file) throws IOException {
//        String filePath=FOLDER_PATH+file.getOriginalFilename();
//        UserImages fileData=fileDataRepository.save(UserImages.builder()
//                .originalName(file.getOriginalFilename())
//                .contentType(file.getContentType())
//                .path(filePath).build());
//
//        file.transferTo(new File(filePath));
//
//        if (fileData != null) {
//            return fileData;
//        }
//        return null;
//    }
//    public MasterImages uploadMaterImageToFileSystem(MultipartFile file) throws IOException {
////        String name=generateImageName();
//        String filePath=FOLDER_PATH_Master+file.getOriginalFilename();
//
//        MasterImages fileData=masterImagesRepository.save(MasterImages.builder()
//                .originalName(file.getOriginalFilename())
//                .contentType(file.getContentType())
//                .path(filePath).build());
//
//        file.transferTo(new File(filePath));
//
//        if (fileData != null) {
//            return fileData;
//        }
//        return null;
//    }

//    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<UserImages> fileData = fileDataRepository.findByOriginalName(fileName);
//        String filePath=fileData.get().getPath();
//        byte[] images = Files.readAllBytes(new File(filePath).toPath());
//        return images;
//    }
}
