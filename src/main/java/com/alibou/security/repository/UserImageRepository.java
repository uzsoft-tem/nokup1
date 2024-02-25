package com.alibou.security.repository;

import com.alibou.security.model.UserImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImages,Long> {
}
