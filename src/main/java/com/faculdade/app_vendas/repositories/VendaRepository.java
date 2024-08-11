package com.faculdade.app_vendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.faculdade.app_vendas.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT v FROM Venda v WHERE v.cliente.nome LIKE %:nome%")
    List<Venda> findByClienteNome(@Param("nome") String nome);

    @Query("SELECT v FROM Venda v WHERE v.funcionario.nome LIKE %:nome%")
    List<Venda> findByFuncionarioNome(@Param("nome") String nome);

    @Query("SELECT v FROM Venda v ORDER BY v.valorTotal DESC")
    List<Venda> findTop10ByOrderByValorTotal();

}
