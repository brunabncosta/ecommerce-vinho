package br.com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {

    private Integer codigo;
    @JsonProperty("tipo_vinho")
    private String tipoVinho;
    private Double preco;
    private String safra;
    @JsonProperty("ano_compra")
    private Integer anoCompra;

}
