package br.com.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompraProdutoDTO {

    private Integer codigo;
    private String tipoProduto;
    private String safra;
    private Integer anoCompra;
    private Double precoUnitario;
    private Integer quantidade;
    private Double subtotal;

    public CompraProdutoDTO(String tipoProduto, String safra, Double preco, Integer quantidade) {
        this.tipoProduto = tipoProduto;
        this.safra = safra;
        this.precoUnitario = preco;
        this.quantidade = quantidade;
    }

}
