package com.faculdade.app_vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.faculdade.app_vendas.repositories.ClienteRepository;
import com.faculdade.app_vendas.services.exceptions.ResourceNotFoundException;

import com.faculdade.app_vendas.entities.Cliente;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Cliente> save(List<Cliente> cliente) {
        return repository.saveAll(cliente);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public Cliente update(Long id, Cliente obj) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " nao encontrado"));

        cliente.setNome(obj.getNome());
        cliente.setEmail(obj.getEmail());
        cliente.setTelefone(obj.getTelefone());
        cliente.setIdade(obj.getIdade());
        cliente.setEndereco(obj.getEndereco());

        return repository.save(cliente);
    }

    public List<Cliente> findClientesByIdadeBetween(Integer minIdade, Integer maxIdade) {
        return repository.findByIdadeEntre(minIdade, maxIdade);
    }
}
