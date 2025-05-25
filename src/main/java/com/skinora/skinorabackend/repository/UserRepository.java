package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Role;
import com.skinora.skinorabackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findByRole(Role role);

}
