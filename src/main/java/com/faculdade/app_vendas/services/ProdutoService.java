package com.faculdade.app_vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.faculdade.app_vendas.repositories.ProdutoRepository;
import com.faculdade.app_vendas.services.exceptions.ResourceNotFoundException;

import com.faculdade.app_vendas.entities.Produto;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Produto> save(List<Produto> produto) {
        return repository.saveAll(produto);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public Produto update(Long id, Produto obj) {
        Produto entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " nao encontrado"));

        entity.setNome(obj.getNome());
        entity.setDescricao(obj.getDescricao());
        entity.setPreco(obj.getPreco());

        return repository.save(entity);
    }

    public List<Produto> findTop10ProdutosMaisCaros() {
        return repository.findTop10ByOrderByPrecoDesc();
    }

}
