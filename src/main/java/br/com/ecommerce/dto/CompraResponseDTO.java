package br.com.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompraResponseDTO {

    private String nomeCliente;
    private String cpfCliente;
    private List<CompraProdutoDTO> produtos;
    private Double valorTotal;

    public CompraResponseDTO(String nomeCliente, String cpf, List<CompraProdutoDTO> produtos, Double valorTotal) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpf;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

}
