package br.com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClienteFielDTO {

    private String nome;
    private String cpf;
    private Integer totalCompras;
    private Double valorTotalGasto;

}
