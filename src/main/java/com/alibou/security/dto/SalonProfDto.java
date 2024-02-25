package com.alibou.security.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SalonProfDto {
    private long usersId;
    private String brandName;
    private String address;
    private long rating;
    private long ratingNUmber;
    private String phoneNumber;
    private String aboutSalon;
    private String timeWork;
    private long viewCount;
    private MultipartFile profImage;
    private String latitude;
    private String longitude;
}
