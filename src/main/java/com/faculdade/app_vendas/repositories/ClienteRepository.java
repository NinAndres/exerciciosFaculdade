package com.faculdade.app_vendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.faculdade.app_vendas.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.idade BETWEEN :minIdade AND :maxIdade")
    List<Cliente> findByIdadeEntre(@Param("minIdade") Integer minIdade, @Param("maxIdade") Integer maxIdade);

}
