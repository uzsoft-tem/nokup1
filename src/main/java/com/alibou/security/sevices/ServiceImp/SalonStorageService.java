package com.alibou.security.sevices.ServiceImp;

import com.alibou.security.repository.SalonFileDataRepository;
import com.alibou.security.repository.SalonImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SalonStorageService {

    @Autowired
    private SalonFileDataRepository salonFileDataRepository;

    @Autowired
    private SalonImageRepository salonImageRepository;

    public String generateImageName(){
        String uuid= String.valueOf(UUID.randomUUID());
        boolean b = salonImageRepository.existsByOriginalNameNot(String.valueOf(uuid));
        return b?uuid:generateImageName();
    }

    private final String FOLDER_PATH= System.getProperty("user.dir") + "\\uploadFiles\\salonGallery\\";
    private final String FOLDER_PATH_image= System.getProperty("user.dir") + "\\uploadFiles\\salonImage\\";

//    public SalonGallery uploadImageToFileSystem(Long salonId,MultipartFile file) throws IOException {
//        String filePath=FOLDER_PATH+file.getOriginalFilename();
//
//        SalonGallery fileData=salonFileDataRepository.save(SalonGallery.builder()
//                .salonId(salonId)
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

//    public SalonImage uploadSalonImageToFileSystem(MultipartFile file) throws IOException {
//        String filePath=FOLDER_PATH_image+file.getName()+"."+file.getContentType().split("/")[1];
////        System.out.println(file.getName()+"."+file.getContentType());
//
//        SalonImage fileData=salonImageRepository.save(SalonImage.builder()
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
}
