package com.alibou.security.repository;


import com.alibou.security.model.UserImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<UserImages,Long> {
    Optional<UserImages> findByOriginalName(String originalName);

    boolean existsByOriginalNameNot(String originalName);
}
