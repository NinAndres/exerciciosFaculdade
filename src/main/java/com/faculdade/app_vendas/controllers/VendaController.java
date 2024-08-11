package com.faculdade.app_vendas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.faculdade.app_vendas.entities.Venda;
import com.faculdade.app_vendas.services.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService service;

    @PostMapping("/save")
    public ResponseEntity<List<Venda>> save(@RequestBody List<Venda> vendas) {
        List<Venda> vendasSalvas = service.save(vendas);
        return ResponseEntity.ok(vendasSalvas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> update(@PathVariable Long id, @RequestBody Venda venda) {
        venda = service.update(id, venda);
        return ResponseEntity.ok().body(venda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Venda>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<Venda>> getVendasByClienteNome(@RequestParam String nome) {
        List<Venda> vendas = service.findVendasByClienteNome(nome);
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/funcionario")
    public ResponseEntity<List<Venda>> getVendasByFuncionarioNome(@RequestParam String nome) {
        List<Venda> vendas = service.findVendasByFuncionarioNome(nome);
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/top10")
    public ResponseEntity<List<Venda>> getTop10VendasComTotaisMaisAltos() {
        List<Venda> vendas = service.findTop10VendasComTotaisMaisAltos();
        return ResponseEntity.ok(vendas);
    }
}
