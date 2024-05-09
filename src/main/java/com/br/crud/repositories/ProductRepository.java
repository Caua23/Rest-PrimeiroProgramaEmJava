package com.br.crud.repositories;

import com.br.crud.models.ProductModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModels, UUID> {



}
