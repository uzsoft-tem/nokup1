package com.alibou.security.repository;

import com.alibou.security.model.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LocationsRepository extends JpaRepository<Locations,Long> {

    @Transactional
    void deleteById(Long id);

}
