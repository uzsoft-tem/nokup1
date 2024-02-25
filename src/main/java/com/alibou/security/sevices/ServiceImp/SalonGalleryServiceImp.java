package com.alibou.security.sevices.ServiceImp;

import com.alibou.security.dto.ApiResponse;
import com.alibou.security.dto.SalonImagesDto;
import com.alibou.security.model.SalonGallery;
import com.alibou.security.repository.SalonGalleryRepository;
import com.alibou.security.sevices.SalonGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SalonGalleryServiceImp implements SalonGalleryService {

    @Autowired
    SalonGalleryRepository salonGalleryRepository;

    @Autowired
    SalonStorageService salonStorageService;


    @Override
    public void AddImages(SalonImagesDto dto) throws IOException {
        for (MultipartFile m : dto.getImages()) {
            SalonGallery salonGallery1=new SalonGallery();
            salonGallery1.setSalonId(dto.getSalonId());
//            System.out.println(m.getOriginalFilename());
            salonGallery1.setOriginalName(m.getOriginalFilename());
            salonGallery1.setContentType(m.getContentType());
            salonGallery1.setImageByte(m.getBytes());
            salonGalleryRepository.save(salonGallery1);
        }
    }

    @Override
    public ApiResponse viewImages(Long salonId) {
        List<SalonGallery> bySalonId = salonGalleryRepository.findAllBySalonId(salonId);
        if (bySalonId.isEmpty()) return new ApiResponse("null",false);
        return new ApiResponse("All Gallery",true,bySalonId);
    }

    @Override
    public void deleteImages(Long salonId) {

    }

    @Override
    public void updateImage(Long id) {

    }
}
