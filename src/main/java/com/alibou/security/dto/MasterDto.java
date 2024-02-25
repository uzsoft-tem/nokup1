package com.alibou.security.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MasterDto {
    private Long salonId;
    private String name;
    private MultipartFile image;
}
