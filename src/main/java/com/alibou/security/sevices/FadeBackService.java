package com.alibou.security.sevices;


import com.alibou.security.dto.ApiResponse;
import com.alibou.security.dto.FadeBackDto;

public interface FadeBackService {
    ApiResponse createFabeBack(FadeBackDto dto);
    ApiResponse updateFadeBack(Long id,FadeBackDto dto);
    ApiResponse getAllFadeBack(Long salonId);
    ApiResponse deleteFadeBack(Long id);
}
