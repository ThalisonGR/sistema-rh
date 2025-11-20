package com.empresa.rh.repository;

import com.empresa.rh.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario , Long> {

    Page<Funcionario> findAll (Pageable pagaPageable);

    @Query("SELECT f FROM Funcionario f WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Funcionario> findByNomeContainingIgnoreCase(@Param("nome") String nome, Pageable pageable);

    List<Funcionario> findAll();
}
