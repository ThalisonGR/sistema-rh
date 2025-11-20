package com.empresa.rh.repository;

import com.empresa.rh.model.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Page<Cargo> findAll (Pageable pagaPageable);

    @Query("SELECT c FROM Cargo c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Cargo> findByNomeContainingIgnoreCase(@Param("nome") String nome, Pageable pageable);

    List<Cargo> findAll();
}
