package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Role;
import com.skinora.skinorabackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<User, Integer> {

    Optional<Role> findByName(String name);

}
