package com.alibou.security.repository;

import com.alibou.security.model.SalonGallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalonGalleryRepository extends JpaRepository<SalonGallery,Long> {
    List<SalonGallery> findAllBySalonId(Long salonId);
    void deleteBySalonId(Long salonId);
}
