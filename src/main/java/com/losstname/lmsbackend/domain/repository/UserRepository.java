package com.losstname.lmsbackend.domain.repository;

import com.losstname.lmsbackend.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
