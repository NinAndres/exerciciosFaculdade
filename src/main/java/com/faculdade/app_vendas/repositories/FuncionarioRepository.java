package com.faculdade.app_vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.app_vendas.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
}
