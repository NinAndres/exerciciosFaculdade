package com.faculdade.app_vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.faculdade.app_vendas.entities.Venda;
import com.faculdade.app_vendas.repositories.VendaRepository;
import com.faculdade.app_vendas.repositories.ProdutoRepository;
import com.faculdade.app_vendas.services.exceptions.ResourceNotFoundException;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Venda findById(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda com ID " + id + " não encontrada"));
    }

    public List<Venda> save(List<Venda> vendas) {
        for (Venda venda : vendas) {
            venda.setValorTotal(venda.calcularValorTotal());
        }
        return vendaRepository.saveAll(vendas);
    }

    public void delete(Long id) {
        try {
            vendaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public Venda update(Long id, Venda obj) {
        Venda entity = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda com ID " + id + " não encontrada"));

        entity.setCliente(obj.getCliente());
        entity.setFuncionario(obj.getFuncionario());
        entity.setProduto(obj.getProduto());
        entity.setObservacao(obj.getObservacao());

        entity.setValorTotal(entity.calcularValorTotal());

        return vendaRepository.save(entity);
    }

    public List<Venda> findVendasByClienteNome(String nome) {
        return vendaRepository.findByClienteNome(nome);
    }

    public List<Venda> findVendasByFuncionarioNome(String nome) {
        return vendaRepository.findByFuncionarioNome(nome);
    }

    public List<Venda> findTop10VendasComTotaisMaisAltos() {
        return vendaRepository.findTop10ByOrderByValorTotal();
    }
}
