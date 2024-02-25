package com.alibou.security.repository;


import com.alibou.security.model.FadeBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FadeBackRepository extends JpaRepository<FadeBack,Long> {
    List<FadeBack> findAllBySalonId(Long salonId);
    @Transactional
    void deleteBySalonId(Long salonId);
}
