package com.alibou.security.repository;

import com.alibou.security.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterRepository extends JpaRepository<Master,Long> {
    List<Master> findBySalonId(Long salonId);
    boolean existsBySalonIdAndName(Long salonId, String name);
    boolean deleteBySalonId(Long salonId);
    boolean existsByIdNotAndName(Long id, String name);
}
