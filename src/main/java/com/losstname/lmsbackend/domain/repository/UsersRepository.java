package com.losstname.lmsbackend.domain.repository;

import com.losstname.lmsbackend.domain.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
