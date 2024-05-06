package com.nota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nota.model.cliente;

@Repository
public interface ClienteRepository extends JpaRepository<cliente, Long>{
    
}
