package com.faculdade.app_vendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.faculdade.app_vendas.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC")
    List<Produto> findTop10ByOrderByPrecoDesc();

}
