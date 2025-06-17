package br.com.ecommerce.controller;

import br.com.ecommerce.service.RecomendacaoProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;


@RestController
public class RecomendacaoController {

    private final RecomendacaoProdutoService recomendacaoProdutoService;

    public RecomendacaoController(RecomendacaoProdutoService recomendacaoProdutoService) {
        this.recomendacaoProdutoService = recomendacaoProdutoService;
    }

    @GetMapping("/recomendacao/{cpf}/tipo")
    public ResponseEntity<?> recomendarTipoVinho(@PathVariable String cpf) {
        Optional<String> tipoRecomendado = recomendacaoProdutoService.recomendarTipoVinhoPorCliente(cpf);
        if (tipoRecomendado.isPresent()) {
            return ResponseEntity.ok(Map.of("tipoRecomendado", tipoRecomendado.get()));
        } else {
            return ResponseEntity.ok("Cliente não encontrado ou sem compras.");
        }
    }
}
