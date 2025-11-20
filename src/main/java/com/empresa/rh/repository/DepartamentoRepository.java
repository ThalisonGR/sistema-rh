package com.empresa.rh.repository;

import com.empresa.rh.model.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
    @Query("SELECT d FROM Departamento d WHERE LOWER(d.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Departamento> findByNomeContainingIgnoreCase(@Param("nome") String nome, Pageable pageable);
    
    List<Departamento> findAll();
}
