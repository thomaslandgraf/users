package com.landgraf.users.infrastructure.repository;

import com.landgraf.SpringProject.infrastructure.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
}
