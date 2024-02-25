package com.alibou.security.sevices;

import com.alibou.security.dto.ApiResponse;
import com.alibou.security.dto.SalonImagesDto;

import java.io.IOException;

public interface SalonGalleryService {
    void AddImages(SalonImagesDto dto) throws IOException;
    ApiResponse viewImages(Long salonId);

    void deleteImages(Long salonId);
    void updateImage(Long id);

}
