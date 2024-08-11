package com.faculdade.app_vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.faculdade.app_vendas.repositories.FuncionarioRepository;
import com.faculdade.app_vendas.services.exceptions.ResourceNotFoundException;

import com.faculdade.app_vendas.entities.Funcionario;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public Funcionario findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Funcionario> save(List<Funcionario> funcionario) {
        return repository.saveAll(funcionario);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public Funcionario update(Long id, Funcionario obj) {
        Funcionario entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario com ID " + id + " nao encontrado"));

        entity.setNome(obj.getNome());
        entity.setEmail(obj.getEmail());
        entity.setTelefone(obj.getTelefone());
        entity.setIdade(obj.getIdade());
        entity.setEndereco(obj.getEndereco());
        entity.setFuncao(obj.getFuncao());

        return repository.save(entity);
    }

}
