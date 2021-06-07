package com.constructora.mundoFuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.constructora.mundoFuturo.models.Material;

public interface IMaterialRepository extends JpaRepository<Material, Long> {

}
