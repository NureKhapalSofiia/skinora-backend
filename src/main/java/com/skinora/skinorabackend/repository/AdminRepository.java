package com.skinora.skinorabackend.repository;

import com.skinora.skinorabackend.entity.Admin;
import com.skinora.skinorabackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Patient, Integer> {

    Optional<Admin> findByUserId(Integer userId);

}
