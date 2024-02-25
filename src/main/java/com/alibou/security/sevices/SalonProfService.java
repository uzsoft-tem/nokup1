package com.alibou.security.sevices;



import com.alibou.security.dto.ApiResponse;
import com.alibou.security.dto.SalonProfDto;

import java.io.IOException;

public interface SalonProfService {
    ApiResponse createSalonProf(SalonProfDto salonProfDto) throws IOException;
    ApiResponse updateSalonProf(Long id,SalonProfDto salonProfDto) throws IOException;
    ApiResponse deleteSalonProf(Long id);
    ApiResponse getSalonProf(Long userId,Long id);
    ApiResponse getAllUserSalonProf(Long userId);
    ApiResponse getAllSalonProf();
}
