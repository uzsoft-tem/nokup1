package com.alibou.security.repository;

import com.alibou.security.model.MasterImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterImagesRepository extends JpaRepository<MasterImages,Long> {
    boolean existsByOriginalNameNot(String originalName);
}
