package com.alibou.security.repository;

import com.alibou.security.model.SalonImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SalonImageRepository extends JpaRepository<SalonImage,Long> {
    boolean existsByOriginalNameNot(String originalName);

    @Transactional
    void deleteById(Long id);
}
