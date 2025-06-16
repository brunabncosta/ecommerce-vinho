package br.com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompraDTO {

    private String nomeCliente;
    private String cpfCliente;
    private Integer codigoProduto;
    private String tipoProduto;
    private Double precoProduto;
    private String safraProduto;
    private Integer anoProduto;
    private Integer quantidade;
    private Double valorTotal;


}
