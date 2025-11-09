package com.empresa.rh.repository;

import com.empresa.rh.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario , Long> {

    @Query("""
        SELECT f FROM Funcionario f
        WHERE (:departamentoId IS NULL OR f.departamento.id = :departamentoId)
        AND (:cargoId IS NULL OR f.cargo.id = :cargoId)
        AND (:chefeId IS NULL OR f.chefe.id = :chefeId)
    """)
    List<Funcionario> findByFiltros(
            @Param("departamentoId") Long departamentoId,
            @Param("cargoId") Long cargoId,
            @Param("chefeId") Long chefeId
    );

}
