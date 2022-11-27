package com.example.demo.persist;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entities.Agency;

public interface AgencyRepository extends JpaRepository<Agency, UUID>, JpaSpecificationExecutor<Agency> {

}
