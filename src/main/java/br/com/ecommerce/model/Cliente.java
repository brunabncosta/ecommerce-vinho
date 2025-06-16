package br.com.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cliente {

    private String nome;
    private String cpf;
    private List<Compra> compras;

}
