package com.alibou.security.repository;

import com.alibou.security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
    boolean existsByUserName(String userName);
    boolean existsByIdNotAndUserName(Long id, String userName);
}
