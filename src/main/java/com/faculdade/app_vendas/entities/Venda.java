package com.faculdade.app_vendas.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToMany
    @JoinTable(name = "venda_produto", joinColumns = @JoinColumn(name = "vendas_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produto;

    private String observacao;
    private Float valorTotal;

    public Float calcularValorTotal() {
        if (produto == null || produto.isEmpty()) {
            return 0f;
        }

        float valorTotal = 0f;
        for (Produto p : produto) {
            valorTotal += p.getPreco();
        }
        return valorTotal;
    }

}
